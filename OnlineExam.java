import java.util.*;

class User
{
    String username,password;
    User(String username,String password)
    {
        this.username=username;
        this.password=password;
    }
     boolean check(String currentname,String currentpassword)
    {
        if(username.equals(currentname)&& password.equals(currentpassword))
            return true;
        else
             return false;
    }
    void changePassword(String newPassword)
    {
        password=newPassword;
    }
}


class Exam
{
    User user;
    String username,password;
    Scanner sc=new Scanner(System.in);
    Timer examTimer;
    Exam()
    {
        examTimer=new Timer();
    }
    void register()
    {
        System.out.println("Enter username:");
        username=sc.nextLine();
        System.out.println("Enter password:");
        password=sc.nextLine();
        user=new User(username, password);
        System.out.println(username+" Successfully registered!");
    }

    void login()
    {
        System.out.println("Enter username:");
        username=sc.nextLine();
        System.out.println("Enter password:");
        password=sc.nextLine();
        if(user==null)
        {
            System.out.println("First do registration!");
        }
        else
        {
         if (user.check(username, password))
         {
            System.out.println("Successfully logined!");
         }
         else
         {
            System.out.println("Not registerd user");
         }
        }
    }

     void updatePassword()
    {
        if(user==null)
        {
            System.out.println("First do login!");
            return;
        }
            System.out.println("Enter your new password:");
            String newPassword=sc.nextLine();
            user.changePassword(newPassword);
    }

    void attendExam()
    {
        if(user==null)
        {
            System.out.println("First do login!");
            return;
        }
        TimerTask endExam =new TimerTask() {
           public void run()
            {
                System.out.println("Time's up! Exam ended.");
                examTimer.cancel();
            }
        };
        examTimer.schedule(endExam,18000);
        boolean timeup=false;
        int mark=0;
        System.out.println("Select the correct answer.Time will ends after 3 minutes.");
        String question[][]={{"Which is the most sensitive organ in our body?"},{"Who invented Computer?"},{"Which is the longest river on Earth?"}};
        String options[][][]={{{"Skin"},{"Brain"},{"Eyes"},{"Tongue"}},{{"Albert Einstein"},{"Thomas Edison"},{"Alan Turing"},{"Charles Babbage"}},{{"Nile"},{"Amazon"},{"Yangtze"},{"Mississippi"}}};
        int[] correctAnswer={1,4,1};
        for(int i=0;i<3;i++)
        {
            System.out.println((question[i][0]));
            for(int j = 0; j < options[i].length; j++) {
                System.out.println((j+1)+"."+options[i][j][0]);
            }
            int option=sc.nextInt();
            if(option==correctAnswer[i])
            {
                System.out.println("Correct answer!");
                mark++;
            }
            else
            {
                System.out.println("Wrong answer. The correct answer is "+options[i][correctAnswer[i]-1][0]);
            }
        }
        if(!timeup)
        {
        System.out.println("Your score is "+mark);
        endExam.cancel();
        }
        
    }

    void closingSession()
    {
        if(user==null)
        {
            System.out.println("First do login!");
            return;
        }
        System.out.println("Session closed!");
    }

    void logOut()
    {
        if(user==null)
        {
            System.out.println("Not logged in!");
            return;
        }
        System.out.println("Session logged out!");
        user=null;
    }


}

class OnlineExam
{
    public static void main(String args[])
    {
        Exam exam=new Exam();
        Scanner sc=new Scanner(System.in);
        int choice=0;
        while(choice!=6)
        {
        System.out.println("Select your choice:");
        System.out.println("1.Register\n2.Login\n3.Update password\n4.Attend Exam\n5.Close Session\n6.Log out");
        choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                exam.register();
                break;
            case 2:
                exam.login();
                break;
            case 3:
                exam.updatePassword();
               
                break;
            case 4:
                exam.attendExam();
                break;
            case 5:
                exam.closingSession();
                break;
            case 6:
                exam.logOut();
                break;
        }
    }
    sc.close();
    }
}

