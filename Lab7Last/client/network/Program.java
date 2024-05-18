package network;

import data.Organization;
import data.generators.IdGenerator;
import data.generators.OrganizationGenerator;

import java.util.Scanner;

public class Program {
    private static final int port = 2555;
    private static final int reconnectionTimeout = 5000;
    private static final int maxReconnectionAttempts = 5;
    public static User user = new User(null,null);
    public void execute() throws InterruptedException {
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("localhost", port, reconnectionTimeout, maxReconnectionAttempts);
        //При отправке запросов проверять только соответсвие типов вводимых данных типам ожидаемых данных
        //все команды проверять на сервере + все команды перенести на сервер, в клиенте должны быть классы reqest, response, client, program, data classes
        System.out.println("Введите register [логин] [пароль], затем введите authorization [логин] [пароль]");
        while (true) {
            String cmd = (scanner.nextLine() + " ").trim();
            input = cmd.split(" ");
            if (input[0].equals("register")){
                if (input.length>=3){
                    user.setLogin(input[1]);
                    user.setPassword(input[2]);
                    System.out.println(client.sendRequest(new Request(input,user)).getResult());
                    user = new User(null,null);
                }
                else{
                    System.out.println("Not enough info for registration");
                }
            }
            else if (input[0].equals("exit_from_account")){
                user = new User(null,null);
                System.out.println("Exit from account confirmed");
            }
            else if (input[0].equals("authorization")){
                if (input.length>=3){
                    user.setLogin(input[1]);
                    user.setPassword(input[2]);
                    System.out.println(client.sendRequest(new Request(input,user)).getResult());
                }
                else{
                    System.out.println("Not enough info for authorization");
                }
            }
            else if ((input[0].equals("add") || input[0].equals("add_if_min") || (input[0].equals("remove_lower"))) && user.getPassword()!= null && user.getLogin()!=null) {
                Organization organization = OrganizationGenerator.createOrganization(IdGenerator.generateId());
                System.out.println(client.sendRequest(new Request(organization, input, user)).getResult());
            }else if(input[0].equals("exit")){
                System.out.println("\n" +
                        "                               #include/*\n" +
                        "                            $r='$c=~s/putchar\n" +
                        "                          /pri';$c=q%#[*/<stdio.h>                  /*-]*/\n" +
                        "                        char*O=\"   +++ +[>++++++++<-]             +++   +++\\\n" +
                        "                    ++++>[  >+>   ++   +<<  -]>>+   +++          +++ [>   +>\\\n" +
                        "               +<<-]>>        <<<<         <..        >>.      ...  ...    .<\\\n" +
                        "           +++[>+   +++         ++++  <-]    >+    +>  >>+.  +++  +++    ++ +\\\n" +
                        "        +++..        ---         -.<   <<.-   ---   --   -----  ..[<   +>>  +<\\\n" +
                        "      -]>              >>   ++   +++     .<    .>   ..<             <-.>     >\\\n" +
                        "    ---    ---.+++      .<   .<   <<     .<.   ..   \";  int/*            >+[ >]\n" +
                        "  [*/    main   (){     int   R,   G,    N[   8],    U;   for  (U=     00;U  <8\n" +
                        " ;N[     U++    ]=0      ){   }for(      U=G    =U   -U     ;O  [R  =G++  ]; ){\n" +
                        " if      (O[   R]==      43   ){++N[U     ];}    else{       if (O[   R]==60 ){\n" +
                        "--        U;}else        {{  ;}     if(    91    ==    O    [R]  ){if(N[     U]\n" +
                        "==                      0){ for      (;   O[    G]-    93;  G++        ){;}G++;\n" +
                        "}}      else{if         (+O [R      ]==  45     ){N     [U]  --;    }else{  if\n" +
                        "(O[R]==62){U++;}        else{R     =G    -1;     if     (O   [R   ]==46     ){\n" +
                        " putchar(N[U]);        }else {    if(    O[R     ]==   93)   {            for\n" +
                        "  (G--;O[G]-          91;   --    G){     }}}     }}    }}      }}return-0;}\n" +
                        "   #/*               (c)   */    line      04    /*>           UG    UU\n" +
                        "      srand         +0;       %;($r       .='   nt+    chr /;$c=~s/\n" +
                        "         ([A-U])   /\\$                   $1          /g;$c=~s/char\\\n" +
                        "              s*\\*\\$O\\s*=/                  \\@O=unpack\"C*\",/;$c=~s\n" +
                        "                      !int/[^/]+/!sub!;')=~s/[\\n    ]//g;eval$r;\n" +
                        "                           eval\"$c;main();\";             #]*/");
                System.exit(1);
            }
            else if (user.getLogin()!= null && user.getPassword()!=null){
                System.out.println(client.sendRequest(new Request(input,user)).getResult());
            }
            else{
                System.out.println("Authorization is required to enter commands");
            }
        }
    }
}


