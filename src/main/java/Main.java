import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Employee e1=new Employee("o","s");
        Employee e2=new Employee("e","a");
        Employee[]database={e1,e2};
        ArrayList<Employee>database2=new ArrayList<>(Arrays.asList(database));

        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String test1=gson.toJson(e1);
        System.out.println("Json: "+test1);
        Employee e3=gson.fromJson(test1,Employee.class);
        System.out.println("From Json: "+e3+'\n');

        System.out.println("Konwersja tablicy obiektów do formatu JSON");
        System.out.println(gson.toJson(database));

        String pathName="C:\\Users\\Student.DESKTOP-VNR47RU\\Desktop\\Lista6ZPO\\json1";
        File file=new File(pathName);
        try(FileWriter fileWriter=new FileWriter(file)){
            gson.toJson(database,fileWriter);
        } catch (IOException e) {
            System.out.println("IO error");
        }
        Employee[]testDatabase=null;
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))){
            testDatabase=gson.fromJson(bufferedReader,Employee[].class);

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("From Json: "+Arrays.toString(testDatabase)+'\n');

        Map m=gson.fromJson(test1,Map.class);
        System.out.println("Obiekt ma: "+m.size()+" atrybuty");
        for(Object key: m.keySet()){
            System.out.println("key: "+key);
        }
        System.out.println(m.get("firstName"));
        System.out.println(m.get("lastName"));
    }
}
