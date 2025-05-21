package espol.poo.topmusical;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 680);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
public class Top10Reorder {
        public void main(String[] args){
            String inputFile = "top10.csv";
            String outputFile = "top10_descendente.csv";

            try{
                List<String> lines = Files.readAllLines(Paths.get(inputFile));

                if (lines.size() < 2){
                    System.out.println("El archivo no tiene suficientes datos.");
                    return;
                }
                
                String header = lines.get(0);
                List<String> songs = lines.subList(1, lines.size());

                songs.sort((a,b) -> {

                    int posA= Integer.parseInt(a.split(",")[0]);
                    int posB= Integer.parseInt(b.split(",")[0]);
                    return Integer.compare(posB, posA);
                });

                List<String> reordered = new ArrayList<>();
                reordered.add(header);
                for(int i = 0; i < songs.size(); i++){
                    String[] parts = songs.get(i).split(",", 2);
                    reordered.add((i+1) + "," + parts[1]);
                }

                Files.write(Paths.get(outputFile), reordered);
                System.out.println("Archivo reordenado en orden descendente: " + outputFile);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
    }

}