import java.util.*;

/**
 * Created by mpadula on 2018-03-13.
 */

public class ChatEngine {
    Map<Long, User> mapaUzytkownikow;
    Map<String, User> mapaUzytkownikow2;
    long iloscUserow;
    public ChatEngine()
    {
        mapaUzytkownikow=new LinkedHashMap<>();
        mapaUzytkownikow2=new LinkedHashMap<>();


        iloscUserow=0;
    }

    public void loginUser(User user) throws UserLoginException
    {
        for(long i=0;i<iloscUserow;i++) {
            User temp=mapaUzytkownikow.get(i);
            if(temp.name==user.name)
            {
                throw new UserLoginException();
            }
        }
        user.setId(iloscUserow);
        mapaUzytkownikow.put(iloscUserow,user);
        mapaUzytkownikow2.put(user.getName(),user);

        iloscUserow++;

    }

    public void logoutUser(long userId) throws UserRemoveException
    {
        User rmv=mapaUzytkownikow.remove(userId);
        if(rmv==null){
            throw new UserRemoveException();
        }

        else{
            mapaUzytkownikow2.remove(rmv.getName());
        }
    }

    public int getNumberOfUsers()
    {
        return mapaUzytkownikow.size();
    }

    public void addUserStar(String userName)
    {
        Set<Map.Entry<Long, User>> entrySet=mapaUzytkownikow.entrySet();

        for(Map.Entry<Long, User> entry: entrySet){
            if(entry.getValue().getName().equals(userName))
            {
                User nowyUser=entry.getValue();
                long liczbagwiazdek=nowyUser.getNumberOfStars();
                liczbagwiazdek++;
                long klucz=entry.getKey();
                nowyUser.setNumberOfStars(liczbagwiazdek);
                mapaUzytkownikow.put(klucz,nowyUser);
            }
        }
    }

    public void removeUserStar(String userName)
    {
        Set<Map.Entry<Long, User>> entrySet=mapaUzytkownikow.entrySet();

        for(Map.Entry<Long, User> entry: entrySet){
            if(entry.getValue().getName()==userName)
            {
                User nowyUser=entry.getValue();
                long liczbagwiazdek=nowyUser.getNumberOfStars();
                liczbagwiazdek--;
                long klucz=entry.getKey();
                nowyUser.setNumberOfStars(liczbagwiazdek);
                mapaUzytkownikow.put(klucz,nowyUser);
            }
        }
    }

    public boolean hasUser(long userId)
    {
        if(mapaUzytkownikow.containsKey(userId))
            return true;
        else return false;
    }

    public boolean hasUser(String userName)
    {
        if(mapaUzytkownikow2.containsKey(userName))
            return true;
        else return false;
    }

    public void broadcastMessage(long userId, String message)
    {

        User tempuser=mapaUzytkownikow.get(userId);
        tempuser.setNumberOfSentMessages(tempuser.getNumberOfSentMessages()+1);
        mapaUzytkownikow.put(userId,tempuser);
        String nameofuser=mapaUzytkownikow.get(userId).getName();
        System.out.println(nameofuser + ": " + message);
        for (Map.Entry<Long,User> entry : mapaUzytkownikow.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue().getName());
        }
    }

    public void showStars(long number)
    {
        User us=mapaUzytkownikow.get(number);
        System.out.println(us.getNumberOfStars());
        System.out.println(us.getName());
        System.out.println(us.getNumberOfSentMessages());
    }

    public void printStatistics()
    {
        System.out.println("Ilość użytkowników to: " + getNumberOfUsers());
        Set<Map.Entry<Long, User>> entrySet=mapaUzytkownikow.entrySet();
        double liczba=0;

        for(Map.Entry<Long, User> entry: entrySet) {
            System.out.println(entry.getKey() + " " + entry.getValue().getName() + " " + " Liczba gwiazdek: " +
                    entry.getValue().getNumberOfStars() + " Liczba wysłanych wiadomości: " + entry.getValue().getNumberOfSentMessages());
            liczba +=entry.getValue().getNumberOfSentMessages();
        }

       Map.Entry<Long, User> wal=Collections.min(entrySet, new Comparator<Map.Entry<Long, User>>() {
            @Override
            public int compare(Map.Entry<Long, User> o1, Map.Entry<Long, User> o2) {
                if(o1.getValue().getNumberOfSentMessages()==o2.getValue().getNumberOfSentMessages())
                    return 0;
                else if(o1.getValue().getNumberOfSentMessages()>o2.getValue().getNumberOfSentMessages())
                    return 1;
                else return -1;
            }
        });

        System.out.print("Minimalna liczba wiadomości: ");
        System.out.println(wal.getValue().getNumberOfSentMessages());
        Map.Entry<Long, User> wal2=Collections.max(entrySet, new Comparator<Map.Entry<Long, User>>() {
            @Override
            public int compare(Map.Entry<Long, User> o1, Map.Entry<Long, User> o2) {
                if(o1.getValue().getNumberOfSentMessages()==o2.getValue().getNumberOfSentMessages())
                    return 0;
                else if(o1.getValue().getNumberOfSentMessages()>o2.getValue().getNumberOfSentMessages())
                    return 1;
                else return -1;
            }
        });
        System.out.print("Maxymalna liczba wiadomości: ");
        System.out.println(wal2.getValue().getNumberOfSentMessages());

        System.out.println("Średnia liczba wiadomości: " + liczba / getNumberOfUsers());




    }

}
