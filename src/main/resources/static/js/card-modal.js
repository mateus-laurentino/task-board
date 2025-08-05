import { validateText } from "./validate-fields.js";

export async function loadManagerModal() {
  const container = document.getElementById("managerModalContainer") || document.body;
  const html = await fetch("card-modal.html").then(res => res.text());

  const style = document.createElement("link");
  style.rel = "stylesheet";
  style.href = "./styles/card-modal.css";
  document.head.appendChild(style);

  const div = document.createElement("div");
  div.innerHTML = html;
  container.appendChild(div);
  
  document.getElementById("closeModal").addEventListener("click", closeModal);
  document.getElementById("cancelBtnModal").addEventListener("click", closeModal);

  const bntSave = document.getElementById("confirmBtnModal");
  bntSave.addEventListener("click", async () => {
    const errors = checkFields();
    if(errors) return alert(errors);

    await checkUpdateCard(bntSave)
  })
}

function closeModal(){
  const historic = document.querySelectorAll(".historic-div");
  historic.forEach(h => h.remove());

  managerReason("none");
  document.getElementById("cardModal").style.display = "none";
}

let cardRef, cardListRef, columnRef, titleInputRef, descriptionInputRef, blockSelectRef, confirmBtnRef;
export async function openCardModal(card, column, cardList) {
  const historicalRespose = await getHistoric(card.id);
  if(!historicalRespose.success)
    return showError(historicalRespose.errors);

  cardRef = card;
  columnRef = column;
  cardListRef = cardList;

  titleInputRef = configureInputs("modalTitleInput", card.title, "input", card.state);
  descriptionInputRef = configureInputs("modalDescriptionInput", card.description, "input", card.state);
  blockSelectRef = configureInputs("modalFormBlockSelect", card.block, "change", card.state);
  confirmBtnRef = document.getElementById("confirmBtnModal");

  const historicalDetails = document.getElementById("historicalDetails");
  historicalRespose.result.forEach(historic => {
    generateHistorical(historicalDetails, historic)
  });
  
  document.getElementById("cardModal").style.display = "flex";
}

function configureInputs(elementId, value, event, state){
  const element = document.getElementById(elementId);
  element.addEventListener(event, checkForChanges);
  element.value = value;

  const immutable = state == "DONE";
  event == "input"
    ? element.readOnly = immutable
    : element.disabled = immutable;

  return element;
}

let titleChangedRef = false; 
let descriptionChangedRef = false;
let blockChangedRef = false;

function checkForChanges() {
  titleChangedRef = titleInputRef.value !== cardRef.title;
  descriptionChangedRef = descriptionInputRef.value !== cardRef.description;
  blockChangedRef = blockSelectRef.value !== (cardRef.block ? "true" : "false");

  const isChanged = titleChangedRef || descriptionChangedRef || blockChangedRef;

  const blockDisplayChange = blockChangedRef ? "block" : "none";
  managerReason(blockDisplayChange);

  if (isChanged && confirmBtnRef.style.display == "block")
    return;

  confirmBtnRef.style.display = isChanged ? "block" : "none";
}

function checkFields(){
  let error = "";

  if(titleChangedRef)
    error += validateText("modalTitleInput", 1, 50, "Título");

  if(descriptionChangedRef)
    error += validateText("modalDescriptionInput", 1, 1000, "Descrição");

  if(blockChangedRef)
    error += validateText("inputBlock", 1, 100, "Motivo");

  return error;
}

async function checkUpdateCard(btnSave){
  let card = await updateCard(cardRef, titleChangedRef, descriptionChangedRef);
  if(!card.success)
    return showError(card.errors);

  card = await changeBlock(card, blockChangedRef);
  if(!card.success)
    return showError(card.errors);

  rebuildUpdatedCard(cardListRef, card.result, columnRef);

  btnSave.style.display = "none";
  managerReason("none");
  closeModal();
}

function managerReason(display){
  document.getElementById("parBlock").style.display = display;
  const input = document.getElementById("inputBlock");
  input.style.display = display;
  input.value = "";
}

async function updateCard(card, titleChanged, descriptionChanged){
  if (!titleChanged && !descriptionChanged)
    return { result: card, success: true };

  var body = {
    title: titleInputRef.value,
    description: descriptionInputRef.value
  }
  
  const response = await fetch(`http://localhost:8080/card/${card.id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  return await response.json();
}

async function changeBlock(card, blockChanged) {
  card = card.result;
  if (!blockChanged) return {result: card, success: true};

  const reason = document.getElementById("inputBlock");
  if (!reason.value)
    return null;

  const response = await fetch(`http://localhost:8080/card/${card.id}/block`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ reason: reason.value }),
  });

  return await response.json();
}

function rebuildUpdatedCard(cardList, cardUpdated, column){
  const cardDiv = document.getElementById(cardUpdated.id);
  cardDiv.remove();

  buildCard(cardList, cardUpdated, column);
}

export function buildCard(cardList, card, column) {
  const cardDiv = document.createElement("div");
  cardDiv.className = "div-card"

  if (card == null)
    return;

  cardDiv.id = card.id;

  checkDrag(card, cardDiv);
  const columnColor = checkBlock(cardDiv, card.block, column.backgroundColorCard, column.backgroundColor);
  
  const stateDiv = generateTagCard(columnColor);
  const detailDiv = generateDetailCard(card, column, cardList);

  cardDiv.appendChild(stateDiv);
  cardDiv.appendChild(detailDiv);

  cardList.appendChild(cardDiv);
}

function checkDrag(card, cardDiv){
  cardDiv.draggable = card.state != 'DONE' && !card.block;
  cardDiv.removeEventListener("dragstart", handleDragStart);

  if(cardDiv.draggable){
    cardDiv.addEventListener("dragstart", (e) => {
      cardDiv.classList.add("dragging");
      e.dataTransfer.setData("text/plain", JSON.stringify(card));
      e.dataTransfer.setData("sourceCardId", card.id);
    });
  }
}

function handleDragStart(e) {
  const cardDiv = e.currentTarget;
  const card = cardDiv.cardData;

  if (!card || card.state === "DONE" || card.block) {
    e.preventDefault();
    return;
  }

  cardDiv.classList.add("dragging");
  e.dataTransfer.setData("text/plain", JSON.stringify(card));
  e.dataTransfer.setData("sourceCardId", card.id);
}

function checkBlock(cardDiv, block, backgroundColorCard, backgroundColor){
  cardDiv.style.background = block
    ? '#ff4646'
    : backgroundColorCard;

  return block ? 'red' : backgroundColor; 
}

function generateTagCard(columnColor){
    const stateDiv = document.createElement("div");
    stateDiv.className = "state-card-tag"

    const stateColor = document.createElement("div");
    stateColor.style.background = columnColor;
    stateColor.className = "state-color-tag"

    stateDiv.appendChild(stateColor);
    return stateDiv;
}

function generateDetailCard(newCard, column, cardList){
  const detailDiv = document.createElement("div");

  const title = document.createElement("a");
  title.setAttribute("draggable", "false");
  title.href = "#";
  title.innerText = newCard.title;
  title.addEventListener("click", async e => {
    e.preventDefault();
    await openCardModal(newCard, column, cardList);
  })
  detailDiv.appendChild(title);
  
  const state = document.createElement("h4");
  state.innerText = newCard.state;

  detailDiv.appendChild(state);
  return detailDiv;
}

async function getHistoric(cardId) {
  const response = await fetch(`http://localhost:8080/card/${cardId}/historic`);
  return await response.json();
}

function generateHistorical(divColumn, historic){
  const historicDiv = document.createElement("div");
  historicDiv.className = "historic-div";

  const description = document.createElement("p");
  description.innerHTML = historic.description;

  historicDiv.appendChild(description);

  divColumn.appendChild(historicDiv);
}

export function showError(errors){
  alert(errors.join('\n'));
}