package br.com.clone.unionmangas.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.domain.Page;

import java.util.List;

// @ApiModel(description = "Model responsável por encapsular todos responses paginados da api.")
@JsonPropertyOrder({ "success", "message", "data", "page" })
public class ResponseBasePaginado<T> extends ResponseBase<T> {

  private static String MESSAGE_SUCCESS = "Operation performed successfully";

  private String message;

  private boolean success = true;

  private T data;

  private PaginacaoBase page;

  public static <T> ResponseBasePaginado<List<T>> of(final Page<T> data) {
    final var response = new ResponseBasePaginado<List<T>>();

    response.setData(data.getContent());
    response.setSuccess(true);
    response.setMessage(MESSAGE_SUCCESS);

    final var page = new PaginacaoBase(data);
    response.setPage(page);

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
  public boolean isSuccess() {
    return this.success;
  }

  @Override
  public ResponseBasePaginado<T> setSuccess(final boolean success) {
    this.success = success;
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

  public PaginacaoBase getPage() {
      return page;
  }

  public ResponseBasePaginado<T> setPage(final PaginacaoBase page) {
    this.page = page;
    return this;
  }

  public <U> ResponseBasePaginado<T> setPaginacao(final Page<U> data) {
    this.page = new PaginacaoBase(data);
    return this;
  }

}
