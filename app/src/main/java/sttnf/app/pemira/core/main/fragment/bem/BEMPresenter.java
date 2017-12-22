package sttnf.app.pemira.core.main.fragment.bem;

import com.google.gson.Gson;

import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.model.Calons;

/**
 * Created by isfaaghyth on 12/17/17.
 * github: @isfaaghyth
 */

public class BEMPresenter extends BasePresenter<BEMView> {

    public BEMPresenter(BEMView view) {
        super.attachView(view);
    }

    Calons getPaslonData() {
        String paslon = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"candidateId\": 1,\n" +
                "            \"name\": \"Ahmad Imaduddin\",\n" +
                "            \"cawapres\": \"Haya Rasikhah\",\n" +
                "            \"header\": \"http://dpm.nurulfikri.ac.id/assets/pemira/assets/img/calon/Ahmad%20Imaduddin.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"candidateId\": 3,\n" +
                "            \"name\": \"Muhammad Abdul Karim\",\n" +
                "            \"cawapres\": \"Chairin Nashrillah\",\n" +
                "            \"header\": \"http://dpm.nurulfikri.ac.id/assets/pemira/assets/img/calon/Muhammad%20Abdul%20Karim.jpg\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return new Gson().fromJson(paslon, Calons.class);
    }

}
