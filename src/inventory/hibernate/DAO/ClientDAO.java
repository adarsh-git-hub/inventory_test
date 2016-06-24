/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAO;

import inventory.hibernate.entities.ClientTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface ClientDAO {

    public boolean saveNewClient(String clientName, double clientsSaleDue, double clientsPurDue, String clientsEmail, long clientsPh1, long clientsPh2, String clientsAddress, String clientsTIN, String clientsServiceNo, String clientType);

    public boolean updateExistingClient(long clientPKeyDB, String clientName, double clientsSaleDue, double clientsPurDue, String clientsEmail, long clientsPh1, long clientsPh2, String clientsAddress, String clientsTIN, String clientsServiceNo, String clientType);

    public boolean deleteExistingClient(long clientPKeyDB);

    public ArrayList<ClientTable> selectAllClients();

    public List getAllClientNames();

    public ClientTable selectClientWithName(String clientName);

    public ClientTable selectClientViaId(long clientPKeyDB);
    
}
