package service.interfaces;

public interface iDB{
    public int getItemIndex(String identifier);
    public boolean deleteItem(String identifier, boolean force);
    public void addItem(Object item);
    public void modifyItem(String identifier, Object newItem);
    public boolean checkExistence(String identifier);

}
