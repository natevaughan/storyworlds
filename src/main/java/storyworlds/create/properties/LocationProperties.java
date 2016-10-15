package storyworlds.create.properties;

/**
 * Created by nvaughan on 10/10/2016.
 */
public class LocationProperties extends AbstractCreatableProperties {

    // credit scarey
    /**
     * Validate fields to ensure expected fields are present. This ensures a RuntimeException
     * is thrown on creation, instead of an obscure NullPointerException being thrown later.
     */
//    private void validateFields() {
//        if (dataGroup == null) {
//            throw new RuntimeException("Invalid cube: no data group");
//        }
//        validateField(dataTypes, "data type");
//        validateField(dateModels, "date model");
//        validateField(markets, "market");
//        validateField(productAttributes, "product attributes");
//        if (type == CubeTypeDTO.CUBE) {
//            validateField(measurementGroups, "measurement groups");
//        } else if (type == CubeTypeDTO.SNAPSHOT) {
//            if (measurementFormulas == null || measurementFormulas.isEmpty()) {
//                throw new RuntimeException("Measures not found. Please add measures to the snapshot.");
//            }
//        }
//    }
//
//    private void validateField(Collection field, String fieldName) {
//        if (field == null || field.isEmpty()) {
//            throw new RuntimeException("Invalid cube or snapshot: no " + fieldName);
//        }
//    }

    public LocationProperties setDescription(String description) {
        this.description = description;
        return this;
    }
}
