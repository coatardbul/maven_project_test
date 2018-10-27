package String;


public class StringMatch {

    public  void  match(){
        String str="multipart/form-data; boundary=--------592316797";

        if(str.matches("[multipart/form\\-data; boundary=]*")){
            System.out.println("1111");
        }
    }
}
