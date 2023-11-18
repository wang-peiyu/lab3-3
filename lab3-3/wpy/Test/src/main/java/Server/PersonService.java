package Server;

import Dao.DataDAO;

public class PersonService {
    private DataDAO dataDAO;

    public PersonService(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }

    public String getContacts() {
        // 调用数据访问层方法查询联系人信息
        return dataDAO.getContacts();
    }

    public boolean addContact(String name, String address, String phone) {
        // 调用数据访问层方法添加联系人
        return dataDAO.addContact(name, address, phone);
    }

    public boolean updateContact(String name, String address, String phone) {
        // 调用数据访问层方法更新联系人信息
        return dataDAO.updateContact(name, address, phone);
    }

    public boolean deleteContact(String name) {
        // 调用数据访问层方法删除联系人
        return dataDAO.deleteContact(name);
    }
}

