/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtce.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ankus
 */
public class ServerLog
{
    private HashMap connection_list; 
    private InetAddress clientIp;
    private int clientPort;
    private long sessionId;
    private String userName;
    boolean block;
    
    ServerLog() //LOG OF ACTIVE CONNECTIONS ON SPECIFIC PORT NUMBERS
    {
        connection_list = new HashMap<Integer, ServerLog>(); //initialize Hashmap from port -> client session
    }
    
    ServerLog(long sessionId, InetAddress ip, String user) //initalize an inidividual client object
    {
        this.sessionId = sessionId;
        clientIp = ip;
        userName = user;
        block = false;
    }
    
    public void setBlock(String username)
    {
        ServerLog l;
        for(int i=0; i<connection_list.size(); i++)
            {
            l = (ServerLog) connection_list.get(i);
            if(l.userName == username){
                l.block = false;
                connection_list.remove(i);
            
            }  
        }
    }     
    
    public boolean getBlock(ServerLog client)
    {
        return client.block;
    }
    
    public void addActiveConnection(ServerLog clientObject, int port) //Map client to port
    {
        connection_list.put(clientObject, port);
    }
    
    public boolean checkConnection(ServerLog clientObject) //check for connection on port number
    {
        ServerLog status = (ServerLog) connection_list.get(clientObject);
        if(status == null)
        {
            return true;
        }
        
        return false;
        
    } 
    
    public long getSessionId()
    {
        return this.sessionId;
    }
    
    
    public void removeActiveConnection(ServerLog clientObject) //remove from list of active connections
    {
        connection_list.remove(clientObject);
    }
    
    public boolean checkOwner()
    {
        if(connection_list.isEmpty())
            return true;
        else
            return false;
    }
    
    
    
    
}