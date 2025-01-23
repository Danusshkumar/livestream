package Controller.FlowController;
import Model.Actors.Uploader;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class UploaderFlowManager {
    public static void initUploaderFlow(){
        Uploader uploader = (Uploader) Database.currentUser;
        while(true){
            Printer.welcomeUser("Uploader");
            Printer.portalWelcomePrinter("Uploader");
            ListPrinter.choicePrinter(Database.uploaderChoice);
            int choice = InputManager.getChoiceInput(Database.uploaderChoice.size());
            switch(choice){
                case 0:
                    return;
                case 1:
                    uploader.viewContentList();
                    break;
                case 2:
                    uploader.viewContent();
                    break;
                case 3:
                    uploader.stream();
                    break;
                case 4:
                    uploader.add();
                    break;
                case 5:
                    uploader.delete();
                    break;
                case 6:
                    uploader.logout();
                    return;
                case 7:
                    uploader.exit();
                    break;
            }
        }
    }
}
