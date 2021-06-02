public class Hit_and_Blow{
  public static void main(String[] args) {
    System.out.println("数字当てゲームをしましょう");
    System.out.println("何桁の数字を当てますか?(2~5桁で指定してください)");
    int count =0;
    int keta = new java.util.Scanner(System.in).nextInt();
    while(count==0){
      if(2 <= keta && 5 >= keta){
        count =1;
      }else{
        System.out.println("桁数は2~5の整数で入力してください");
        System.out.println("何桁の数字を当てますか?(2~5桁で指定してください)");
        keta = new java.util.Scanner(System.in).nextInt();
      }
    }
    System.out.println(keta+"桁の数字をあててください");
    System.out.println("桁と数字が両方合っている場合ヒット、数字のみが合ってる場合ブローと出力されます");
    System.out.println("同じ数字は二度使いません");
    System.out.println("15ターン以内で正解できなかったらあなたの負けです");
    int[] A =settingAnser(keta);
    int time = 0;
    int win =0;
    int lose =0;
    int[] ans =new int[keta];

    while(win==0 && lose==0){
      time++;
      if(time==15){
        lose=1;
      }
      System.out.println("〜〜〜〜〜"+time+"回目〜〜〜〜〜");
      System.out.println(keta+"桁の数字を入力してください");
      count =1;
      while(count >0){
        count =0;
        String as = new java.util.Scanner(System.in).nextLine();
        int a =  Integer.parseInt(as);
        ans =change(a,keta);
        count =judge(ans,as,keta);
        if(count==1){
          System.out.println("同じ数字を使わないでください");
          System.out.println(keta+"桁の数字を入力してください");
        }else if(count==2){
          System.out.println("正しく入力してください");
          System.out.println(keta+"桁の数字を入力してください");
        }
      }
      int hit = countHit(ans,A);
      int blow = countBlow(ans,A,hit);
      System.out.println(hit+"ヒット"+blow+"ブロー!");
      if(hit==keta){
        win=1;
      }
      }
      if(win==1){
        System.out.println("正解!おめでとうございます!");
        System.out.print("正解は");
        for(int i=keta-1;i>=0;i--){
          System.out.print(A[i]);
        }
        System.out.print("でした！");
        System.out.println("かかったターン数は"+time+"ターン");
      }else{
        System.out.println("15ターンで正解できなかったためあなたの負けです");
        System.out.print("正解は");
        for(int i=keta-1;i>=0;i--){
          System.out.print(A[i]);
        }
        System.out.print("でした！");
        System.out.println();
      }
  }

  public static int[] settingAnser(int keta){
    int count =0;
    int[] zeroToNine = new int[10];
    int[] A =new int[keta];
    for(int i=0;i<zeroToNine.length;i++){
      zeroToNine[i]=i;
    }
    for(int i=0;i<A.length;i++){
      count =0;
      while(count !=1){
        int num = new java.util.Random().nextInt(zeroToNine.length);
        if(zeroToNine[num]!=-1){
          zeroToNine[num] = -1;
          A[i]=num;
          count =1;
        }
      }
    }
    return A;
  }

  public static int[] change(int a,int keta){
    int[] ans = new int[keta];
    double calc = 0;
    for(int i =0;i<ans.length;i++){
      calc =Math.pow(10,i);
      int calci = (int)calc;
      ans[i]=a/calci %10;
    }
    return ans ;
  }

  public static int judge(int[] ans,String as,int keta){
    int count =0;
    for(int i=0;i<ans.length;i++){
      for(int j=0;j<ans.length;j++){
        if(i != j && ans[i]==ans[j]){
          count = 1;
        }
      }
    }
    as = "10"+as;
    int asJudge =  Integer.parseInt(as);
    if(asJudge<Math.pow(10,keta+1)||asJudge>Math.pow(10,keta+2)){
      count=2;
    }
    return count;
  }

  public static int countHit(int[] ans,int[] A){
    int hit = 0;
    for(int i=0;i<ans.length;i++){
      if(ans[i]==A[i]){
        hit++;
      }
    }
    return hit;
  }

  public static int countBlow(int[] ans,int[] A,int hit){
    int blow =-hit;
    for(int i=0;i<ans.length;i++){
      for(int j=0;j<ans.length;j++){
        if(ans[i]==A[j]){
          blow++;
        }
      }
    }
    return blow;
  }

}
