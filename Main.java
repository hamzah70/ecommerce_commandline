import java.util.ArrayList;
import java.util.Scanner;


class input {
    static Scanner inp;
    input() {
        inp=new Scanner(System.in);
    }
}

interface reward {
    int getReward();
    void setReward(int a);
    String getName();
    int getAddress();
}

class Merchant implements reward{
    private String name;
    private int address;
    private double contribution=0;
    private ArrayList<String> item_name = new ArrayList<>();
    private ArrayList<Integer> item_price = new ArrayList<>();
    private ArrayList<Integer> item_quantity = new ArrayList<>();
    private ArrayList<String> item_category = new ArrayList<>();
    private ArrayList<String> offers = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    int item_count=0;
    private int reward= 0;

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public void setReward(int reward) {
        this.reward = reward;
    }

    public Merchant(String name, int address, int contribution){
        this.name = name;
        this.address = address;
        this.contribution = contribution;
    }

    void Add(String name, int price, int quantity, String category){
        this.item_name.add(name);
        this.item_price.add(price);
        this.item_quantity.add(quantity);
        this.item_category.add(category);
        this.offers.add("None");
        this.item_count+=1;
        this.id.add(item_count);
    }

    void Add(int price, int quantity, int index){
        this.item_price.set(index, price);
        this.item_quantity.set(index, quantity);
    }

    int query3_index_find(Merchant m, String category){
        int index = -1;
        for(int i=0; i<m.item_category.size();i++){
            String s = m.item_category.get(i);
            if(s.equals(category)){
                index=i;
                break;
            }
        }
        return index;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getAddress(){
        return address;
    }

    public double getContribution(){
        return contribution;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(int address){
        this.address = address;
    }

    public void setContribution(double contribution){
        this.contribution += contribution;
    }

    public void setItem_quantity(int value, int index){ this.item_quantity.set(index, value); }

    public String getItem_name(int index) {
        return item_name.get(index);
    }

    public Integer getItem_price(int index) {
        return item_price.get(index);
    }

    public Integer getItem_quantity(int index) {
        return item_quantity.get(index);
    }

    public String getItem_category(int index) {
        return item_category.get(index);
    }

    public String getOffers(int index) {
        return offers.get(index);
    }

    public void setOffers(int index, String value) {
        offers.set(index, value);
    }
}

class Customer implements reward{
    private String name;
    private int address;
    private ArrayList<String> orders_placed = new ArrayList<>();
    private int no_of_orders;
    int account_balance;
    int reward_balance;
    int purchases;
    private int reward=0;
    static ArrayList<String> unique_category = new ArrayList<>();
    ArrayList<String> cart_item_name = new ArrayList<>();
    ArrayList<Integer> cart_item_quantity = new ArrayList<>();
    ArrayList<Double> cart_item_price = new ArrayList<>();
    ArrayList<String> cart_merchant_name = new ArrayList<>();
    ArrayList<Merchant> cart_merchant = new ArrayList<>();
    ArrayList<Integer> cart_merchant_item_index = new ArrayList<>();

    public Customer(String name, int address, int orders_placed){
        this.name = name;
        this.address = address;
        this.no_of_orders = orders_placed;
        this.account_balance=100;
        this.reward_balance=0;
        this.purchases=0;
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getAddress(){
        return address;
    }

    public ArrayList<String> getOrders_placed(){
        return orders_placed;
    }

    public void setName(String name){
        this.name = name;
    }

    public void add_unique_category(String category){
        if(!this.unique_category.contains(category)){
            this.unique_category.add(category);
        }
    }

    public void setAddress(int address){
        this.address = address;
    }

    public void setOrders_placed(String orders_placed){
        this.orders_placed.add(orders_placed);
    }
}



public class Main {

    public static void merchant(ArrayList<Merchant> merchants, ArrayList<Customer> customer){
        System.out.println("Choose Merchant");
        for(int i=0; i<merchants.size(); i++){
            Merchant m=merchants.get(i);
            System.out.println(m.getAddress()+" "+m.getName());
        }
        int choice = input.inp.nextInt();
        Merchant m = merchants.get(choice-1);
        String name = m.getName();
        int val=0;
        while(val!=6){
            System.out.println("Welcome "+name);
            System.out.println("Merchant Menu");
            System.out.println("1) Add item");
            System.out.println("2) Edit item");
            System.out.println("3) Search by category");
            System.out.println("4) Add offer");
            System.out.println("5) Rewards won");
            System.out.println("6) Exit");
            val = input.inp.nextInt();
            if(val==1){
                System.out.println("Enter item details");
                System.out.println("item name:");
                String namee=input.inp.next();
                System.out.println("item price:");
                int price=input.inp.nextInt();
                System.out.println("item quantity:");
                int quantity=input.inp.nextInt();
                System.out.println("item category:");
                String category = input.inp.next();
                m.Add(namee, price, quantity, category);
                System.out.println(m.item_count+" "+namee+" "+price+" "+quantity+" None "+category);
                if(!Customer.unique_category.contains(category)){
                    Customer.unique_category.add(category);
                }
            }

            else if(val==2){
                System.out.println("choose item by code");
                for(int i=0;i<m.item_count;i++){
                    System.out.println(m.id.get(i)+" "+m.getItem_name(i)+" "+m.getItem_price(i)+" "+m.getItem_quantity(i)+" "+
                                       m.getOffers(i)+" "+m.getItem_category(i));
                }
                int id = input.inp.nextInt();
                System.out.println("Enter edit details");
                System.out.println("item price:");
                int price = input.inp.nextInt();
                System.out.println("item quantity:");
                int quantity = input.inp.nextInt();
                m.Add(price, quantity, id-1);
                System.out.println(m.id.get(id-1)+" "+m.getItem_name(id-1)+" "+m.getItem_price(id-1)+" "+
                        m.getItem_quantity(id-1)+" "+m.getOffers(id-1)+" "+m.getItem_category(id-1));
            }

            else if(val==3){
                System.out.println("Choose a Category");
                for(int i=0;i<m.item_count;i++){
                    System.out.println(m.id.get(i)+") "+m.getItem_category(i));
                }
                int id = input.inp.nextInt();
                String category = m.getItem_category(id-1);
                for(int i=0;i<merchants.size();i++){
                    Merchant temp=merchants.get(i);
                    int index = temp.query3_index_find(temp, category);
                    if(index!=-1){
                        System.out.println(temp.id.get(index)+" "+temp.getItem_name(index)+" "+temp.getItem_price(index)+" "+
                                        temp.getItem_quantity(index)+" "+temp.getOffers(index)+" "+temp.getItem_category(index));
                    }
                }
            }

            else if(val==4){
                System.out.println("choose item by code");
                for(int i=0;i<m.item_count;i++){
                    System.out.println(m.id.get(i)+" "+m.getItem_name(i)+" "+m.getItem_price(i)+" "+m.getItem_quantity(i)+" "+
                                       m.getOffers(i)+" "+m.getItem_category(i));
                }
                int id=input.inp.nextInt();
                System.out.println("1) buy one get one");
                System.out.println("2) 25% off");
                int offer_choice = input.inp.nextInt();
                if(offer_choice==1){
                    m.setOffers(id-1, "BOGO");
                }
                else if(offer_choice==2){
                    m.setOffers(id-1, "25%");
                }
                System.out.println(m.id.get(id-1)+" "+m.getItem_name(id-1)+" "+m.getItem_price(id-1)+" "+
                        m.getItem_quantity(id-1)+" "+m.getOffers(id-1)+" "+m.getItem_category(id-1));
            }

            else if(val==5){
                System.out.println("No. of slots rewarded are: "+ m.getReward());
            }
        }
    }

    public static void customer(ArrayList<Customer> customer, ArrayList<Merchant> merchant){
        System.out.println("Choose Customer");
        for(int i=0; i<customer.size(); i++){
            int n=i+1;
            Customer c = customer.get(i);
            System.out.println(n+" "+c.getName());
        }
        int choice = input.inp.nextInt();
        Customer c = customer.get(choice-1);
        String name = c.getName();

        while(true){
            System.out.println("Welcome "+name);
            System.out.println("Customer Menu");
            System.out.println("1) Search Item");
            System.out.println("2) Checkout Cart");
            System.out.println("3) Reward Won");
            System.out.println("4) Print Latest Orders");
            System.out.println("5) Exit");
            int val = input.inp.nextInt();
            if(val==1){
                System.out.println("Choose a category");
                for(int i=0; i<Customer.unique_category.size(); i++){
                    int ind=i+1;
                    System.out.println(ind+") "+Customer.unique_category.get(i));
                }
                int category_id = input.inp.nextInt();
                String category_name = Customer.unique_category.get(category_id-1);
                int count = 1;
                ArrayList<Merchant> cnt = new ArrayList<>();

                System.out.println("choose item by code");
                for(int i=0;i<merchant.size();i++){
                    Merchant temp=merchant.get(i);

                    int index = temp.query3_index_find(temp, category_name);
                    if(index!=-1){
                        System.out.println(count+" "+temp.getItem_name(index)+" "+temp.getItem_price(index)+" "+
                                temp.getItem_quantity(index)+" "+temp.getOffers(index)+" "+temp.getItem_category(index));
                        count+=1;
                        cnt.add(temp);
                    }
                }
                System.out.println("Enter item code");
                int item_code = input.inp.nextInt();
                System.out.println("Enter item quantity");
                int item_quantity = input.inp.nextInt();
                System.out.println("Choose method of transaction");
                System.out.println("1) Buy item");
                System.out.println("2) Add item to cart");
                System.out.println("3) Exit");
                int transaction = input.inp.nextInt();
                int purchase_before = c.purchases/5;
                int purchase_after = (c.purchases+1)/5;
                Merchant temp = cnt.get(item_code-1);
                int index = temp.query3_index_find(temp, category_name);
                double price = 0;
                int extra_quantity = 0;
                if(temp.getOffers(index).equals("25%")){
                    price = (0.005+.75)*(item_quantity*temp.getItem_price(index));
                }
                else{
                    price = 1.005*item_quantity*temp.getItem_price(index);
                }
                if(temp.getOffers(index).equals("BOGO")){
                    extra_quantity = item_quantity;
                }

                if(transaction==1){
                    if(temp.getItem_quantity(index)>=item_quantity){
                        if(c.account_balance+c.reward_balance>=price){
                            c.purchases = c.purchases+1;
                            if(c.account_balance>=price){
                                c.account_balance-=price;
                            }
                            else{
                                price-=c.account_balance;
                                c.account_balance = 0;
                                c.reward_balance-=price;
                            }
                            if(purchase_after-purchase_before>0){
                                c.reward_balance += (purchase_after-purchase_before)*10;
                                c.setReward(c.getReward()+ (purchase_after-purchase_before)*10);
                            }
                            company_profit += (0.01*price);
                            int contribution_before = (int)temp.getContribution()/100;
                            temp.setContribution(0.005*price);
                            int contribution_after = (int)temp.getContribution()/100;
                            if(contribution_after-contribution_before>0){
                                temp.setReward(temp.getReward()+(contribution_after-contribution_before));
                            }
                            temp.setItem_quantity(temp.getItem_quantity(index)-item_quantity, index);
                            int quant = temp.getItem_quantity(index);
                            if(quant>=extra_quantity){
                                item_quantity+=extra_quantity;
                                temp.setItem_quantity(temp.getItem_quantity(index)-extra_quantity, index);
                            }
                            else{
                                item_quantity+=quant;
                                temp.setItem_quantity(0, index);
                            }
                            System.out.println("Item Successfully bought");
                            String orders = "Bought item "+ temp.getItem_name(index)+" quantity: "+item_quantity
                                    +" for Rs "+price+" from Merchant "+temp.getName();
                            c.setOrders_placed(orders);

                        }
                        else{
                            System.out.println("Insufficent Balance");
                        }
                    }
                    else{
                        System.out.println("Insufficient Quantity");
                    }
                }
                else if (transaction == 2){
                    System.out.println("Item Added to Cart Successfully");
                    c.cart_item_name.add(temp.getItem_name(index));
                    c.cart_item_price.add(price);
                    c.cart_item_quantity.add(item_quantity);
                    c.cart_merchant_name.add(temp.getName());
                    c.cart_merchant.add(temp);
                    c.cart_merchant_item_index.add(index);
                }
            }
            else if(val==2){
                for(int i=0;i<c.cart_item_name.size();i++){
                    double price = c.cart_item_price.get(i);
                    int quantity = c.cart_item_quantity.get(i);
                    String item_name = c.cart_item_name.get(i);
                    String merchant_name = c.cart_merchant_name.get(i);
                    Merchant temp = c.cart_merchant.get(i);
                    int index = c.cart_merchant_item_index.get(i);
                    int extra_quantity = 0;
                    if(temp.getOffers(index).equals("25%")){
                        price = 0.75*price;
                    }
                    if(temp.getOffers(index).equals("BOGO")){
                        extra_quantity = quantity;
                    }
                    if(temp.getItem_quantity(index)>=quantity){
                        if(c.account_balance+c.reward_balance>=price){
                            if(c.account_balance>=price){
                                c.account_balance-=price;
                            }
                            else{
                                price-=c.account_balance;
                                c.account_balance=0;
                                c.reward_balance-=price;
                            }
                            temp.setItem_quantity(temp.getItem_quantity(index)-extra_quantity, index);
                            int purchase_before = c.purchases/5;
                            int purchase_after = (c.purchases+1)/5;
                            if(purchase_after-purchase_before>0){
                                c.reward_balance+=(purchase_after-purchase_before)*10;
                                c.setReward(c.getReward()+ (purchase_after-purchase_before)*10);
                            }
                            c.purchases+=1;
                            int quant = temp.getItem_quantity(index);
                            if(quant>=extra_quantity){
                                quantity+=extra_quantity;
                                temp.setItem_quantity(temp.getItem_quantity(index)-quant, index);
                            }
                            else{
                                quantity+=quant;
                                temp.setItem_quantity(0, index);
                            }
                            company_profit += (0.01*price);
                            int contribution_before = (int)temp.getContribution()/100;
                            temp.setContribution(0.005*price);
                            int contribution_after = (int)temp.getContribution()/100;
                            if(contribution_after-contribution_before>0){
                                temp.setReward(temp.getReward()+(contribution_after-contribution_before));
                            }
                            String orders = "Bought item "+ item_name+" quantity: "+quantity
                                    +" for Rs "+price+" from Merchant "+merchant_name;
                            c.setOrders_placed(orders);
                        }
                    }
                }
                while(c.cart_merchant.size()>0){
                    c.cart_merchant_item_index.remove(0);
                    c.cart_merchant.remove(0);
                    c.cart_item_name.remove(0);
                    c.cart_merchant_name.remove(0);
                    c.cart_item_quantity.remove(0);
                    c.cart_item_price.remove(0);
                }
            }
            else if(val==3){
                System.out.println("Reward Balance is "+ c.getReward());
            }
            else if(val==4){
                ArrayList orders = c.getOrders_placed();
                if(orders.size()!=0){
                    int i = orders.size()-1;
                    int j = 10;
                    while(i>=0 && j>0){
                        System.out.println(orders.get(i));
                        i-=1;
                        j-=1;
                    }

                }
                else{
                    System.out.println("No order has been placed");
                }
            }
            else if(val==5){
                System.out.println("--End of sample test--");
                System.exit(0);
            }
        }

    }

    static double company_profit=0;

    public static void main(String[] args){
        input yolo=new input();
        ArrayList<Merchant> merchant = new ArrayList<>();
        Merchant temp1 = new Merchant("jack",1, 0);
        Merchant temp2 = new Merchant("john",2, 0);
        Merchant temp3 = new Merchant("james",3, 0);
        Merchant temp4 = new Merchant("jeff",4, 0);
        Merchant temp5 = new Merchant("joseph",5, 0);
        merchant.add(temp1);
        merchant.add(temp2);
        merchant.add(temp3);
        merchant.add(temp4);
        merchant.add(temp5);

        ArrayList<Customer> customer = new ArrayList<>();
        Customer c1= new Customer("ali", 1, 0);
        Customer c2= new Customer("nobi", 2, 0);
        Customer c3= new Customer("bruno", 3, 0);
        Customer c4= new Customer("borat", 4, 0);
        Customer c5= new Customer("aladeen", 5, 0);
        customer.add(c1);
        customer.add(c2);
        customer.add(c3);
        customer.add(c4);
        customer.add(c5);


        while(true){
            System.out.println("Welcome to Mercury");
            System.out.println("1) Enter as Merchant");
            System.out.println("2) Enter as Customer");
            System.out.println("3) See user details");
            System.out.println("4) Company account balance");
            System.out.println("5) Exit");
            int choice = input.inp.nextInt();
            if(choice==1){
                merchant(merchant, customer);
            }
            else if(choice==2){
                customer(customer, merchant);
            }
            else if(choice==3){
                System.out.println("Select Merchant or Customer");
                String s = input.inp.next();
                if(s.equals("Customer")){
                    System.out.println("Select a User:");
                    for(int i=0; i<customer.size(); i++){
                        Customer c = customer.get(i);
                        System.out.println(c.getAddress()+") "+c.getName());
                    }
                    int val = input.inp.nextInt();
                    Customer c = customer.get(val-1);
                    System.out.println("User details are:");
                    System.out.println("User Id: " +c.getAddress());
                    System.out.println("User Name: "+c.getName());
                }
                else{
                    System.out.println("Select a User:");
                    for(int i=0; i<merchant.size(); i++){
                        Merchant m = merchant.get(i);
                        System.out.println(m.getAddress()+") "+m.getName());
                    }
                    int val = input.inp.nextInt();
                    Merchant m = merchant.get(val-1);
                    System.out.println("User details are:");
                    System.out.println("User Id: " +m.getAddress());
                    System.out.println("User Name: "+m.getName());
                }

            }
            else if(choice==4){
                System.out.println("Company Account Balance: "+ company_profit);
            }
            else{
                System.out.println("--End of sample test--");
                break;
            }
        }
    }
}
