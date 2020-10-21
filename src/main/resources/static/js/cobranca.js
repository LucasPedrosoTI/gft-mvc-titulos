$("#confirmacaoExclusaoModal").on("show.bs.modal", function (event) {
  const button = $(event.relatedTarget);
  const id = button.data("id");
  const descricao = button.data("descricao");
  const modal = $(this);
  const form = modal.find("form");
  let action = form.attr("action");

  if (!action.endsWith("/")) {
    action += "/";
  }

  form.attr("action", action + id);

  modal
    .find(".modal-body span")
    .html(
      "Tem certeza que deseja excluir o título <strong>" +
        descricao +
        "</strong>?"
    );
});

$("#confirmacaoExclusaoModal").on("hide.bs.modal", function (_event) {
  $(this).find("form").attr("action", "/titulos/");
});
