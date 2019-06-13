import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.util.Map;
import java.sql.*;
//javac -cp sqlite-jdbc-3.23.1.jar; JuniorProject.java
//java -cp sqlite-jdbc-3.23.1.jar; JuniorProject
//path %path%;C:\Program Files\Java\jdk1.8.0_131\bin;

public class JuniorProject{
	public static void main(String [] args) throws IOException{
	
		int port = 8500;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Database   db =  new  Database("jdbc:sqlite:data.db" ); 
		String query = "SELECT * FROM Hospitals inner join location on Hospitals.FaciltyName=Location.FacilityName";
		String result = db.selectData(query);
		server.createContext("/I", new RouteHandler(result));


		String query2 = "SELECT * FROM Patient";
		String result2 = db.selectData(query2);
		server.createContext("/pat", new RouteHandler(result2));


        server.start();


        String html=Input.readFile("exampleJSON.html");
		
		System.out.println("Server started at port " + port);
	}
}