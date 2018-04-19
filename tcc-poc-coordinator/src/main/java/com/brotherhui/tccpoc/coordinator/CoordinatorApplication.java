package com.brotherhui.tccpoc.coordinator;

import com.atomikos.icatch.tcc.rest.Server;

/**
 * @author hunter
 * @create 2018-04-13 11:28 AM
 */
public class CoordinatorApplication {
    public static void main(String[] args) {
        Server server = new Server("http://localhost:9090");
        server.start();
    }
}
