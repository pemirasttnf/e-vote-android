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
                "            \"capres\": \"Ahmad Imaduddin\",\n" +
                "            \"cawapres\": \"Haya Rasikhah\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"candidateId\": 2,\n" +
                "            \"capres\": \"Muhammad Abdul Karim\",\n" +
                "            \"cawapres\": \"Chairin Nashrillah\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return new Gson().fromJson(paslon, Calons.class);
    }

}
