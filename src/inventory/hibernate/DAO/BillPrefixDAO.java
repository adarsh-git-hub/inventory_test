/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAO;

import inventory.hibernate.entities.BillPrefixTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface BillPrefixDAO {

    public boolean saveNewBillPrefix(String billPrefix, String billName, String ourEmail, String ourTin,
            String ourDescription, String ourAddress, String ourServiceNo, long ourPh1, long ourPh2,
            String bankAcc1, String ifsc1, String bankAcc2, String ifsc2);

    public boolean updateExistingBillPrefix(long billPrefixPKeyDB, String billPrefix, String billName, String ourEmail,
            String ourTin, String ourDescription, String ourAddress, String ourServiceNo, long ourPh1, long ourPh2,
            String bankAcc1, String ifsc1, String bankAcc2, String ifsc2);

    public boolean deleteExistingBillPrefix(long billPrefixPKeyDB);

    public ArrayList<BillPrefixTable> selectAllBillPrefix();

    public List getAllBillPrefixNames();

    public BillPrefixTable selectBillPrefixWithName(String billPrefix);

    public BillPrefixTable selectBillPrefixViaId(long billPrefixPKeyDB);
}
