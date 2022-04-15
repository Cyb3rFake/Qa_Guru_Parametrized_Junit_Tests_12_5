package guru.qa.domain;

public enum MenueItem {
    IMG("Картинки"), VID("Видео");

   public final String rusName;

   MenueItem(String rusName){
       this.rusName = rusName;
   }
}
