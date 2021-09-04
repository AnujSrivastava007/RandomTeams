//import java.util.Random;
//
//public class randomizeTeams {
//    Random random=new Random();
//
//		System.out.println("Enter num of team mem");
//    int n= Integer.parseInt(br.readLine());
//
//		System.out.println("Enter num of team to be made");
//    int tn= Integer.parseInt(br.readLine());
//
//    String TeamMem[]=new String[n];
//		System.out.println("Enter names");
//		for(int i=0; i<n; i++)
//    {
//        TeamMem[i]=br.readLine();
//    }
//    //System.out.println("Done");
//    String Teams[][]=new String[tn][(n/tn)];
//
//		for(int i=0; i<tn; i++)
//    {
//        for(int j=0; j<(n/tn); j++)
//        {
//            int rn=random.nextInt(n);
//
//            if(TeamMem[rn]!="")
//            {
//                Teams[i][j]=TeamMem[rn];
//                TeamMem[rn]="";
//            }
//            else
//            {
//                --j;
//                continue;
//            }
//        }
//    }
//
//		for(int i=0; i<tn; i++)
//    {
//        System.out.println("Team "+(i+1)+":");
//        for(int j=0; j<(n/tn); j++)
//        {
//            System.out.println((j+1)+". "+Teams[i][j]);
//        }
//    }
//}
