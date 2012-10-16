package com.healthcare.mongoDB;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

public class MongoDB {
	//each mongo instance has a default collection pool of 10 cons
	private static Mongo mongo = null;
	private DB db = null;
	private static MongoDB self = null;
	private ArrayList<ServerAddress> mongoServers = null;
	
	private MongoDB () {
		try{
			String servers = System.getProperty("serverList");
			String[] hostPorts = servers.split(",");
			mongoServers = new ArrayList<ServerAddress>();
//			for (String hostPort:hostPorts){
				mongoServers.add(new ServerAddress("127.0.0.1", 27017));
//			}      
			mongo = new Mongo(mongoServers);
			db = mongo.getDB(System.getProperty("mongoDBname"));
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	public static MongoDB getInstance(){
		if(self == null)
			self = new MongoDB();
		
		return self;
	}
	
	public void put(String dbCollectionName, BasicDBObject doc){
		try{
			DBCollection conn = db.getCollection(dbCollectionName);
			WriteResult result = conn.insert(doc);
		}
		catch(Throwable t){
			t.printStackTrace();	
		}
	}
	
}
