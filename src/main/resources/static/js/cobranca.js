$("#confirmacaoExclusaoModal").on("show.bs.modal", function (event) {
  const button = $(event.relatedTarget);

  const id = button.data("id");
  const descricao = button.data("descricao");

  const modal = $(this);
  const form = modal.find("form");

  let action = form.data("url-base");

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

$(function () {
  $('[rel="tooltip"]').tooltip();
  $(".js-currency").maskMoney({
    decimal: ",",
    thousands: ".",
    allowZero: true,
  });

  $(".js-atualizar-status").on("click", async function (event) {
    event.preventDefault();

    const botaoReceber = $(event.currentTarget);
    const url = botaoReceber.attr("href");

    try {
      const response = await fetch(url, {
        method: "PUT",
      });

      if (response.ok) {
        const id = botaoReceber.data("id");
        const status = await response.text();
        $("[data-role=" + id + "]").html(
          '<span class="badge badge-success">' + status + "</span>"
        );

        botaoReceber.hide();
      }
    } catch (error) {
      console.log(error);
      alert("Erro recebendo cobrança");
    }
  });
});
