package br.com.clone.unionmangas.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.util.List;

@ApiModel(description = "Model responsável por encapsular todos responses paginados da api.")
@JsonPropertyOrder({ "sucess", "message", "data", "paginacao" })
public class ResponseBasePaginado<T> extends ResponseBase<T> {

  public static final String OPERACAO_SUCESSO = "Operação realizada com sucesso.";

  @ApiModelProperty(position = 2)
  private String message;

  @ApiModelProperty(position = 1)
  private boolean sucess = true;

  @ApiModelProperty(position = 3)
  private T data;

  @ApiModelProperty(position = 4)
  private PaginacaoBase paginacao;

  public static <T> ResponseBasePaginado<List<T>> of(final Page<T> data) {
    final var response = new ResponseBasePaginado<List<T>>();

    response.setData(data.getContent());
    response.setSucess(true);
    response.setMessage(OPERACAO_SUCESSO);

    final var paginacao = new PaginacaoBase(data);
    response.setPaginacao(paginacao);

    return response;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public ResponseBasePaginado<T> setMessage(final String message) {
    this.message = message;
    return this;
  }

  @Override
  public boolean isSucess() {
    return this.sucess;
  }

  @Override
  public ResponseBasePaginado<T> setSucess(final boolean sucess) {
    this.sucess = sucess;
    return this;
  }

  @Override
  public T getData() {
    return this.data;
  }

  @Override
  public ResponseBasePaginado<T> setData(final T entidade) {
    this.data = entidade;
    return this;
  }

  public PaginacaoBase getPaginacao() {
    return this.paginacao;
  }

  public ResponseBasePaginado<T> setPaginacao(final PaginacaoBase paginacao) {
    this.paginacao = paginacao;
    return this;
  }

  public <U> ResponseBasePaginado<T> setPaginacao(final Page<U> data) {
    this.paginacao = new PaginacaoBase(data);
    return this;
  }

}
