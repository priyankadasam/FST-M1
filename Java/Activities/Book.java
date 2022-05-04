package activities;


     abstract class Book{
        String title;
        abstract void setTitle(String s);
        String getTitle(){
            return title;
        }
    }
     class MyBook extends Book{
        public void setTitle(String s) {
            title = s;
        }
    }

