package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

        public class Main {

            public static void main(String[] args) {
                Path resourceDirectory= Paths.get("src");
                String absolutePath=resourceDirectory.toFile().getAbsolutePath();
                File f1=new File(absolutePath+"/testData_Apartments.txt");
                Scanner sc=null;
                List<Apartment> appartment=new ArrayList<>();
                Set<String> rabotesthi=new HashSet<>();
                Map<String,Integer> broiAprtm=new HashMap<>();

                try{
                    sc=new Scanner(f1);
                    while(sc.hasNext()){
                        String city=sc.next();
                        int  rooms=sc.nextInt();
                        int kvad=sc.nextInt();
                        int price=sc.nextInt();
                        String telefon=sc.next();

                        Apartment neww=new Apartment(city,rooms,kvad,price,telefon);
                        if(rooms==3 && kvad>=100 && (city.equals("София") || city.equals("Бургас") || city.equals("Варна"))){
                            appartment.add(neww);
                        }
                        if(!broiAprtm.containsKey(city)){
                            broiAprtm.put(city,1);

                        } else{
                            broiAprtm.put(city,broiAprtm.get(city)+1);
                        }

                    }
                    if(appartment.size()==0){
                        throw new UnsuitableApartmentException();
                    }


                }catch(FileNotFoundException e){
                    System.out.println(e.getMessage());
                }catch(UnsuitableApartmentException e){
                    System.out.println("Wir haben keine Wohnungen! Entschuldigung!");
                }finally{

                }
                Collections.sort(appartment);
                for(int i=0;i<3;i++) {
                    if (!rabotesthi.contains(appartment.get(i).getNumber())) {
                        rabotesthi.add(appartment.get(i).getNumber());
                    }
                }
                PrintWriter output=null;
                try{
                    Map<String, Integer> zaGrad = new HashMap<>();
                    List<Map.Entry<String, Integer>> list = new ArrayList<>(broiAprtm.entrySet());
                    list.sort(Map.Entry.comparingByValue());
                    String city1=list.get(3).getKey();
                    for (Map.Entry<String, Integer> entry : list) {
                        zaGrad.put(entry.getKey(), entry.getValue());
                    }
                    broiAprtm=zaGrad;
                    File smth=new File(absolutePath+"/output.txt");
                    output.println(city1);
                    output=new PrintWriter(smth);
                    for(String s:rabotesthi){
                        output.println( s);}
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally{
                    output.close();
                }

            }
        }
