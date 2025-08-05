export function validateText(fieldId, min, max, fildName){
    const field = document.getElementById(fieldId);
    const lengthFiel = field?.value?.trim()?.length;

    if (lengthFiel >= min && lengthFiel <= max){
        field.classList.remove("error-text")
        return "";
    }

    field.classList.add("error-text")
    return `O campo ${fildName} não pode ser vazio e ter no máximo ${max} caracteres\n`;
}