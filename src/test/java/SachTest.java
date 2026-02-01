import dao.SachDAO;
import model.Sach;

public class SachTest {
    public static void main(String[] args) {

        Sach sach1 = new Sach(
                "SP03",
                "Lap Trinh Java",
                10,
                500000,
                "Giao trinh",
                2024
        );

        SachDAO dao = new SachDAO();
        dao.insert(sach1);

    }
}
