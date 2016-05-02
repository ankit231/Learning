/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

/**
 * @version 1.0, 02-Mar-2016
 * @author ankit
 */
public class ESImpl {
    Client client = null;

    ESImpl() {
        Map<String, String> settingsMap = new HashMap<String, String>();
        settingsMap.put("isActive", "not_analyzed");
        Node node = nodeBuilder().clusterName("elasticsearch").settings(Settings.settingsBuilder().put(settingsMap)).client(true).node();
        this.client = node.client();
    }

    public static void main(String a[]) {
        // Initializing Node and Client
        ESImpl esImpl = new ESImpl();

        String index = "persondb";
        String type = "person";
        System.out.println("******* Enter int value to call a method ********");
        System.out.println("1 for Indexing");
        System.out.println("2 for searching Person by ID");
        System.out.println("3 for searching person by FirstName");
        System.out.println("4 for range filter");
        Scanner in = new Scanner(System.in);
        try {
            int methodIdentifier = in.nextInt();
            switch (methodIdentifier) {
                case 1:
                    // Indexing data
                    esImpl.indexData(index, type);
                    break;

                case 2:
                    // Searching Person By ID
                    esImpl.searchPersonByID(index, type);
                    break;

                case 3:
                    // Searching Person By First Name
                    esImpl.searchPersonByFirstName(index, type);
                    break;

                case 4:
                    // Searching Person By First Name
                    esImpl.filterQueryExample(index, type);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    private ArrayList<Person> filterQueryExample(String index, String type) {
        ArrayList<Person> personList = new ArrayList<Person>();
        System.out.println("********* Range Query Example **************");

        try {
            BoolQueryBuilder query = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("gender", "male")).filter(QueryBuilders.termQuery("eyeColor", "blue")).filter(
                    QueryBuilders.rangeQuery("age").from(20).to(30).includeLower(true).includeUpper(true));

            String[] includes = { "gender", "eyeColor", "name.firstName", "age" };

            SearchResponse response = client.prepareSearch(index).setTypes(type).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(query) // Query
            //.addFields("gender", "eyeColor", "name.firstName")
            .setFetchSource(includes, null).setSize(20).execute().actionGet();

            SearchHit[] results = response.getHits().getHits();

            for (SearchHit hit : results) {
                Person person = null;
                String jsonString = hit.getSourceAsString();
                person = new Gson().fromJson(jsonString, Person.class);
                personList.add(person);
            }

        } catch (Exception e) {
            System.out.println("Error Occurred in range query");
        }
        System.out.println("PersonList : " + personList);
        return personList;
    }

    private Person searchPersonByID(String index, String type) {
        Person person = null;
        System.out.println("********* Searching Person by ID **************");

        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter Person ID that need to be searched");
            String id = in.next();
            SearchResponse response = client.prepareSearch(index).setTypes(type).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.termQuery("_id", id)) // Query
            .execute().actionGet();

            SearchHit[] results = response.getHits().getHits();

            // Since search for ID, expecting a single result here
            SearchHit searchHit = results[0];

            String jsonString = searchHit.getSourceAsString();
            person = new Gson().fromJson(jsonString, Person.class);
        } catch (Exception e) {
            System.out.println("Error Occurred while getting person for ID");
        } finally {
            in.close();
        }
        return person;
    }

    private ArrayList<Person> searchPersonByFirstName(String index, String type) {
        ArrayList<Person> personList = new ArrayList<Person>();
        System.out.println("********* Searching Person by First Name **************");

        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter First Name that need to be searched");
            String firstName = in.next();
            SearchResponse response = client.prepareSearch(index).setTypes(type).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(
                    QueryBuilders.matchQuery("name.firstName", firstName)) // Query
            .setPostFilter(QueryBuilders.rangeQuery("age").from(20).to(30).includeLower(true).includeUpper(true)).execute().actionGet();

            SearchHit[] results = response.getHits().getHits();

            for (SearchHit hit : results) {
                Person person = null;
                String jsonString = hit.getSourceAsString();
                person = new Gson().fromJson(jsonString, Person.class);
                personList.add(person);
            }

        } catch (Exception e) {
            System.out.println("Error Occurred while getting person from first name");
        } finally {
            in.close();
        }
        return personList;
    }

    private void indexData(String index, String type) {
        try {
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            JSONArray jsonArr = this.readJson();
            for (int i = 0; i < jsonArr.size(); i++) {
                Map<String, Object> obj = (Map<String, Object>) jsonArr.get(i);
                bulkRequest.add(client.prepareIndex(index, type, i + "").setSource(obj));
            }
            BulkResponse bulkResponse = bulkRequest.execute().actionGet();
            if (bulkResponse.hasFailures()) {
                System.out.println("Failure Occurred");
            }
        } catch (Exception e) {
            System.out.println("Errror Occurred");
        }
    }

    private JSONArray readJson() {
        JSONArray jsonArray = null;
        JSONParser parser = new JSONParser();
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader("/home/ankit/Desktop/JsonFile"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}