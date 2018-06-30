package pe.edu.upc.tratohechotec.models;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class DocumentType {
    private int id;
    private String description;

    public DocumentType() {
    }

    public DocumentType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public DocumentType setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DocumentType setDescription(String description) {
        this.description = description;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", getId());
        bundle.putString("Description", getDescription());
        return bundle;
    }

    public static class Builder {
        DocumentType documentType;

        public Builder() {
            documentType = new DocumentType();
        }

        public Builder(DocumentType documentType) {
            this.documentType = documentType;
        }

        public DocumentType build() {
            return documentType;
        }

        public static Builder from(JSONObject jsonDocument) {
            try{
                return new Builder(new DocumentType(
                        jsonDocument.getInt("id"),
                        jsonDocument.getString("description")
                ));
            }
            catch (JSONException e){
                e.printStackTrace();;
                return null;
            }
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new DocumentType(
                    bundle.getInt("Id"),
                    bundle.getString("Description")
            ));
        }
    }
}
