import java.util.Scanner;
class Product {
    private String name;
    private double price;
    private int count;

    public Product(String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public int getCount() {
      return count;
    }
    public int setCount(int num) {
      count-=num;
      return count;
    }
}
class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void purchase(Product product) {
        System.out.println(name + " purchased " + product.getName() + " for Rs." + product.getPrice());
    }
}

class Seller {
    private String name;
    private Product[] products;
    private int productCount;

    Seller(String name, int maxSize) {
        this.name = name;
        this.products = new Product[maxSize];
        this.productCount = 0;
    }

    void addProduct(String productName, double price, int count) {
        if (productCount < products.length) {
            products[productCount++] = new Product(productName, price, count);
            System.out.println(productName + " added successfully.");
        } else {
            System.out.println("Product array is full. Cannot add " + productName);
        }
    }

    Product getProduct(String productName,int n) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(productName))
             {
              if(products[i].getCount()>=n){
                products[i].setCount(n);
                return products[i];
              }
              else if(products[i].getCount()<n)
              {
              System.out.println("Stock Range Exceed!!!");
              return null;
              }
              else if(products[i].getCount()==0)
              {
                System.out.println("Out of Stock!!!");
                return null;
              }
             }
            
        }
        System.out.println(productName + " not found.");
        return null;
    }
    void showProduct()
    {
      for(int i=0;i<productCount&&products[i]!=null;i++)
      {
        System.out.println("\nProduct Name: "+products[i].getName()+" >>> Price: Rs."+products[i].getPrice()+" >>> No of Products: "+products[i].getCount());
      }
    }
}

class StoreManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter seller name: ");
        String sellerName = scanner.nextLine();
        System.out.print("Enter maximum number of products: ");
        int maxSize = scanner.nextInt();
        scanner.nextLine(); 

        Seller seller = new Seller(sellerName, maxSize);

        while (true) {

            System.out.println("\nInventory of "+sellerName);
            System.out.println("_________________________");
            System.out.println("\nOptions:");
            System.out.println("1. Add Product");
            System.out.println("2. Purchase Product");
            System.out.println("3. Show Products");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Its a newline character

            switch (option) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Enter product Quantity: ");
                    int productQuantity = scanner.nextInt();
                    scanner.nextLine(); 
                    seller.addProduct(productName, productPrice, productQuantity);
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    Customer customer = new Customer(customerName);
                    System.out.print("Enter product name to purchase: ");
                    String purchaseProductName = scanner.nextLine();
                    System.out.print("Enter number of products want to buy: ");
                    int number = scanner.nextInt();
                    Product product = seller.getProduct(purchaseProductName,number);
                    if (product != null) {
                        customer.purchase(product);
                    }
                    break;
                case 3:
                      seller.showProduct();
                      break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
