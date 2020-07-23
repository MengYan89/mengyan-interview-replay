import java.util.*;

public class test_three {
    public static void main(String[] args) {
        String str = "oneofthecentralresultsofairesearchinthe1970swasthattoachievegoodperformanceaisystemsmusthavelargeamountsofknowledgeknowledgeispowertheslogangoeshumansclearlyusevastamountsofknowledgeandifaiistoachieveitslongtermgoalsaisystemsmustalsousevastamountssincehandcodinglargeamountsofknowledgeintoasystemisslowtediousanderrorpronemachinelearningtechniqueshavebeendevelopedtoautomaticallyacquireknowledgeoftenintheformofifthenrulesproductionsunfortunatelythishasoftenledtoautilityproblemminton1988bthelearninghascausedanoverallslowdowninthesystemforexampleinmanysystemslearnedrulesareusedtoreducethenumberofbasicstepsthesystemtakesinordertosolveproblemsbypruningthesystemssearchspaceforinstancebutinordertodetermineateachstepwhichrulesareapplicablethesystemmustmatchthemagainstitscurrentsituationusingcurrenttechniquesthematcherslowsdownasmoreandmorerulesareacquiredsoeachsteptakeslongerandlongerthisectcanoutweighthereductioninthenumberofstepstakensothatthenetresultisaslowdownthishasbeenobservedinseveralrecentsystemsminton1988aetzioni1990tambeetal1990cohen1990ofcoursetheproblemofslowdownfromincreasingmatchcostisnotrestrictedtosystemsinwhichthepurposeofrulesistoreducethenumberofproblemsolvingstepsasystemacquiringnewrulesforanypurposecanslowdowniftherulessignicantlyincreasethematchcostandintuitivelyoneexpectsthatthemoreproductionsthereareinasystemthehigherthetotalmatchcostwillbethethesisofthisresearchisthatwecansolvethisprobleminabroadclassofsystemsbyimprovingthematchalgorithmtheyuseinessenceouraimistoenablethescalingupofthenumberofrulesinproductionsystemsweadvancethestateoftheartinproductionmatchalgorithmsdevelopinganimprovedmatchalgorithmwhoseperformancescaleswellonasignicantlybroaderclassofsystemsthanexistingalgorithmsfurthermorewedemonstratethatbyusingthisimprovedmatchalgorithmwecanreduceoravoidtheutilityprobleminalargeclassofmachinelearningsystems";
        Map<String, Integer> map = new TreeMap<String, Integer>();
        final int maxNum = 0;
        char[] ch = str.toCharArray();
        // 每两个
        for (int i = 0; i < ch.length - 1; i++) {
            map.put(ch[i] + "" + ch[i + 1], map.get(ch[i] + "" + ch[i + 1]) == null ? 1 : map.get(ch[i] + "" + ch[i + 1]) + 1);
        }
        int temp=0;
        String strMax="";
        System.out.println(str.length());
        for (Map.Entry<String, Integer> mapping : map.entrySet()) {

            if(temp<mapping.getValue()){
                temp= mapping.getValue();
                strMax= mapping.getKey();
            }

        }
        System.out.print("str:"+strMax + "\n出现" +temp +"次数");

    }
}
