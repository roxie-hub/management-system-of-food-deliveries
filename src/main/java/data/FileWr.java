package data;

import java.io.*;

public class FileWr{

    public String getUserRole(String user, String pass) {
        String ch=null, l=null;
        try {
            FileReader file = new FileReader("user.txt");
            BufferedReader buf = new BufferedReader(file);
            l = buf.readLine();
            while(l != null){
                String[] bucatele = l.split("/");
                if(bucatele[1].equals(user) && bucatele[2].equals(pass)){
                    ch=bucatele[0];
                }
                l = buf.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ch;
    }

    public void writeClientInFile(String user, String pass) {
        try {
            FileWriter file = new FileWriter("user.txt",true);
            BufferedWriter buff = new BufferedWriter(file);
            {
                buff.append("\n");
                buff.append("c/");
                buff.append(user + "/");
                buff.append(pass);
                buff.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int verifyAcc(String user, String pass) {
        String l=null;
        try {
            FileReader file = new FileReader("user.txt");
            BufferedReader buf = new BufferedReader(file);
            l = buf.readLine();
            while(l != null){
                String[] bucatica = l.split("/");
                if(bucatica[1].equals(user)){
                    return 1;
                }
                l = buf.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
