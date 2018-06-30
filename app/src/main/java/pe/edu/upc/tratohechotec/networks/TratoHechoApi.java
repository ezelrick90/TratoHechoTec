package pe.edu.upc.tratohechotec.networks;

public class TratoHechoApi {
    private static String BASE_URL = "http://movilesupc.somee.com/api/";

    public static String postSpecialistAuthenticationsUrl()
    {
        return BASE_URL + "specialists/authentications";
    }

    public static String getSpecialistQuotationsUrl(int specialistId)
    {
        return BASE_URL + "specialists/"+String.valueOf(specialistId)+"/quotations";
    }

    public static String putSpecialistQuotationsUrl(int specialistId, int quotationId)
    {
        return BASE_URL + "specialists/"+String.valueOf(specialistId)+"/quotations/"+String.valueOf(quotationId);
    }
}
