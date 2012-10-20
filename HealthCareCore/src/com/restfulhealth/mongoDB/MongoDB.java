package com.restfulhealth.mongoDB;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
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
			mongo.setWriteConcern(WriteConcern.SAFE);
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
	
	/**
	 * BasicDBObject:
	 * 		PatientID
	 * 		DocumentName
	 * 		ClinlicDocum (BSON)
	 * @param dbCollectionName
	 * @param obj
	 * @throws Throwable
	 */
	public void put(String dbCollectionName, BasicDBObject obj) throws Throwable{
		try{
			DBCollection conn = db.getCollection(dbCollectionName);
			WriteResult result = conn.insert(obj);	
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
	}
	
	
	public ArrayList<DBObject> query(String dbCollectionName, BasicDBObject query) throws Throwable{
		ArrayList<DBObject> objs = new ArrayList<DBObject>();
		DBCursor cursor = null;
		try{
			DBCollection conn = db.getCollection(dbCollectionName);
			if(query == null) //find all
				cursor = conn.find();
			else
				cursor = conn.find(query);
			
			while (cursor.hasNext()){
				objs.add(cursor.next());
			}			
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		return objs;
	}
	
}
