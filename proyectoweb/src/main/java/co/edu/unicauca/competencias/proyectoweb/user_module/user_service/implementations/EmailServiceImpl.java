package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces.iEmailService;
import com.azure.communication.email.EmailAsyncClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollerFlux;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.resourcemanager.communication.CommunicationManager;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailServiceImpl implements iEmailService {

    @Override
    public void connectWithDomain() {
        AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);
        TokenCredential credential = new DefaultAzureCredentialBuilder()
                .authorityHost(profile.getEnvironment().getActiveDirectoryEndpoint())
                .build();
        CommunicationManager manager = CommunicationManager
                .authenticate(credential, profile);

        List<String> linkedDomains = new ArrayList<>();
        linkedDomains.add("/subscriptions/5d5b0767-9461-4632-a3aa-af341c785294/resourceGroups/ACSE1/providers/Microsoft.Communication/emailServices/1-Email-Com-Resource/domains/AzureManagedDomain");

        manager
                .communicationServices()
                .define("AzureManagedDomain")
                .withRegion("Global")
                .withExistingResourceGroup("ACSE1")
                .withDataLocation("United States")
                .withLinkedDomains(linkedDomains)
                .create();
    }

    @Override
    public void sendTokenByEmail(String email, String token) {
        connectWithDomain();
        String connectionString = "endpoint=https://1-email-connection.unitedstates.communication.azure.com/;accesskey=BJ2CnLcsnErj1HaAeXWGVoqgUE0Z7zXvvaZF9lnbLpdkk0DuHc8LJQQJ99AKACULyCpVPDoZAAAAAZCS2Vx8";

        //String endpoint = "https://1-email-connection.unitedstates.communication.azure.com";
        //AzureKeyCredential azureKeyCredential = new AzureKeyCredential("BJ2CnLcsnErj1HaAeXWGVoqgUE0Z7zXvvaZF9lnbLpdkk0DuHc8LJQQJ99AKACULyCpVPDoZAAAAAZCS2Vx8");

        EmailAsyncClient emailAsyncClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildAsyncClient();
        /*EmailAsyncClient emailAsyncClient = new EmailClientBuilder()
                .endpoint(endpoint)
                .credential(azureKeyCredential)
                .buildAsyncClient();*/

        EmailAddress toAddress = new EmailAddress(email);

        EmailMessage message = new EmailMessage()
                .setSenderAddress("DoNotReply@712d482a-78c8-4aa8-8015-b1b626d9a6b1.azurecomm.net")
                .setToRecipients(toAddress)
                .setSubject("¡Welcome to SISGECO!")
                .setBodyPlainText("This email message is sent from Azure Communication Services Email using the Java SDK.")
                .setBodyHtml("""
                        <html>
                            <body>
                                <h1>Confima el TOKEN: `${token}` </h1>
                            </body>
                        </html>
                        """);

        Duration MAIN_THREAD_WAIT_TIME = Duration.ofSeconds(30);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        PollerFlux<EmailSendResult, EmailSendResult> poller = emailAsyncClient.beginSend(message);

        executorService.submit(() -> {
           poller.subscribe(
                   response -> {
                       if (response.getStatus() == LongRunningOperationStatus.SUCCESSFULLY_COMPLETED){
                           System.out.println("Successfully sent the email (operation id: " + response.getValue().getId() + ")");
                       }
                       else {
                           System.out.println("Email send status: " + response.getStatus() + ", operation id: " + response.getValue().getId());
                       }
                   },
                   error -> {
                       System.out.println("Error occurred while sending email: " + error.getMessage());
                   }
           );
        });

        try {
            Thread.sleep(MAIN_THREAD_WAIT_TIME.toMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("Main thread ends.");

    }


}
