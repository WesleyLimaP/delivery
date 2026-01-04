<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Pedido Cancelado</title>
</head>
<body style="margin:0; padding:0; background-color:#f4f6f8; font-family:Arial, sans-serif;">

<table width="100%" cellpadding="0" cellspacing="0" style="background-color:#f4f6f8; padding:20px 0;">
    <tr>
        <td align="center">

            <!-- CONTAINER -->
            <table width="600" cellpadding="0" cellspacing="0"
                   style="background-color:#ffffff; border-radius:8px; overflow:hidden; box-shadow:0 2px 6px rgba(0,0,0,0.1);">

                <!-- HEADER -->
                <tr>
                    <td style="background-color:#d32f2f; padding:20px; text-align:center; color:#ffffff;">
                        <h1 style="margin:0; font-size:24px;">Pedido Cancelado ❌</h1>
                        <p style="margin:5px 0 0; font-size:14px;">
                            Seu pedido foi cancelado
                        </p>
                    </td>
                </tr>

                <!-- BODY -->
                <tr>
                    <td style="padding:20px; color:#333333; font-size:14px;">

                        <p style="margin:0 0 10px;">
                            <strong>Cliente:</strong>
                            ${(pedido.cliente.nome)!'—'}
                        </p>

                        <p style="margin:0 0 20px;">
                            <strong>Restaurante:</strong>
                            ${(pedido.restaurante.nome)!'—'}
                        </p>

                        <p style="margin:0 0 20px; color:#555555;">
                            Informamos que o pedido abaixo foi <strong>cancelado</strong>.
                            Caso tenha realizado algum pagamento, o estorno seguirá as regras
                            da forma de pagamento utilizada.
                        </p>

                        <!-- ITENS -->
                        <table width="100%" cellpadding="0" cellspacing="0"
                               style="border-collapse:collapse; margin-bottom:20px;">
                            <tr style="background-color:#fdecea;">
                                <th align="left" style="padding:10px; border:1px solid #dddddd;">Produto</th>
                                <th align="center" style="padding:10px; border:1px solid #dddddd;">Qtd</th>
                                <th align="right" style="padding:10px; border:1px solid #dddddd;">Valor</th>
                            </tr>

                            <#list pedido.itens as item>
                                <tr>
                                    <td style="padding:10px; border:1px solid #dddddd;">
                                        ${(item.produto.nome)!'Produto'}
                                    </td>
                                    <td align="center" style="padding:10px; border:1px solid #dddddd;">
                                        ${item.quantidade!0}
                                    </td>
                                    <td align="right" style="padding:10px; border:1px solid #dddddd;">
                                        R$ ${item.precoTotal!0}
                                    </td>
                                </tr>
                            </#list>
                        </table>

                        <!-- TOTAL -->
                        <p style="font-size:16px; margin:0 0 10px;">
                            <strong>Valor do pedido:</strong>
                            <span style="color:#d32f2f;">
                                R$ ${pedido.valorTotal!0}
                            </span>
                        </p>

                        <p style="margin:0;">
                            <strong>Forma de pagamento:</strong>
                            ${pedido.formaDePagamento.descricao!'—'}
                        </p>

                    </td>
                </tr>

                <!-- FOOTER -->
                <tr>
                    <td style="background-color:#f0f2f5; padding:15px; text-align:center; font-size:12px; color:#666666;">
                        <p style="margin:0;">
                            Este é um e-mail automático. Em caso de dúvidas, entre em contato com o suporte.
                        </p>
                    </td>
                </tr>

            </table>
            <!-- /CONTAINER -->

        </td>
    </tr>
</table>

</body>
</html>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Pedido Cancelado</title>
</head>
<body style="margin:0; padding:0; background-color:#f4f6f8; font-family:Arial, sans-serif;">

<table width="100%" cellpadding="0" cellspacing="0" style="background-color:#f4f6f8; padding:20px 0;">
    <tr>
        <td align="center">

            <!-- CONTAINER -->
            <table width="600" cellpadding="0" cellspacing="0"
                   style="background-color:#ffffff; border-radius:8px; overflow:hidden; box-shadow:0 2px 6px rgba(0,0,0,0.1);">

                <!-- HEADER -->
                <tr>
                    <td style="background-color:#d32f2f; padding:20px; text-align:center; color:#ffffff;">
                        <h1 style="margin:0; font-size:24px;">Pedido Cancelado ❌</h1>
                        <p style="margin:5px 0 0; font-size:14px;">
                            Seu pedido foi cancelado
                        </p>
                    </td>
                </tr>

                <!-- BODY -->
                <tr>
                    <td style="padding:20px; color:#333333; font-size:14px;">

                        <p style="margin:0 0 10px;">
                            <strong>Cliente:</strong>
                            ${(pedido.cliente.nome)!'—'}
                        </p>

                        <p style="margin:0 0 20px;">
                            <strong>Restaurante:</strong>
                            ${(pedido.restaurante.nome)!'—'}
                        </p>

                        <p style="margin:0 0 20px; color:#555555;">
                            Informamos que o pedido abaixo foi <strong>cancelado</strong>.
                            Caso tenha realizado algum pagamento, o estorno seguirá as regras
                            da forma de pagamento utilizada.
                        </p>

                        <!-- ITENS -->
                        <table width="100%" cellpadding="0" cellspacing="0"
                               style="border-collapse:collapse; margin-bottom:20px;">
                            <tr style="background-color:#fdecea;">
                                <th align="left" style="padding:10px; border:1px solid #dddddd;">Produto</th>
                                <th align="center" style="padding:10px; border:1px solid #dddddd;">Qtd</th>
                                <th align="right" style="padding:10px; border:1px solid #dddddd;">Valor</th>
                            </tr>

                            <#list pedido.itens as item>
                                <tr>
                                    <td style="padding:10px; border:1px solid #dddddd;">
                                        ${(item.produto.nome)!'Produto'}
                                    </td>
                                    <td align="center" style="padding:10px; border:1px solid #dddddd;">
                                        ${item.quantidade!0}
                                    </td>
                                    <td align="right" style="padding:10px; border:1px solid #dddddd;">
                                        R$ ${item.precoTotal!0}
                                    </td>
                                </tr>
                            </#list>
                        </table>

                        <!-- TOTAL -->
                        <p style="font-size:16px; margin:0 0 10px;">
                            <strong>Valor do pedido:</strong>
                            <span style="color:#d32f2f;">
                                R$ ${pedido.valorTotal!0}
                            </span>
                        </p>

                        <p style="margin:0;">
                            <strong>Forma de pagamento:</strong>
                            ${pedido.formaDePagamento.descricao!'—'}
                        </p>

                    </td>
                </tr>

                <!-- FOOTER -->
                <tr>
                    <td style="background-color:#f0f2f5; padding:15px; text-align:center; font-size:12px; color:#666666;">
                        <p style="margin:0;">
                            Este é um e-mail automático. Em caso de dúvidas, entre em contato com o suporte.
                        </p>
                    </td>
                </tr>

            </table>
            <!-- /CONTAINER -->

        </td>
    </tr>
</table>

</body>
</html>
