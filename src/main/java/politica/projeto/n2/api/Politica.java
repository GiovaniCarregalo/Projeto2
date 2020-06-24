package politica.projeto.n2.api;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Politica {
        private int id;
        private String date;

        private int value;

        public Politica(){
            this.id = -1;

            this.date = null;
            this.value = 0;
        }

        public Politica(int id, String date, int value) {
            this.id = id;
            this.date = date;
            this.value = value;
        }

        public int getId(){ return id;}

        public void setId(int id){this.id = id;}

        public String getDate() {
            return date;
        }


        public void setDate(String date) {
            this.date = date;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    @Override
    public String toString() {
        return "Politica{" +
                "id=" + id +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}

