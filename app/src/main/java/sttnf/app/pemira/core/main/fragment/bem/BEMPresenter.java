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
                "            \"candidateId\": 09fc24d9-6165-4071-8ec3-83f66256a6b4,\n" +
                "            \"capres\": \"Ahmad Imaduddin\",\n" +
                "            \"cawapres\": \"Haya Rasikhah\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"candidateId\": fb468841-8143-4313-a90f-47e56c800c82,\n" +
                "            \"capres\": \"Muhammad Abdul Karim\",\n" +
                "            \"cawapres\": \"Chairin Nashrillah\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return new Gson().fromJson(paslon, Calons.class);
    }

}
