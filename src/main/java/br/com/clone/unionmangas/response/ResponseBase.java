package br.com.clone.unionmangas.response;

public class ResponseBase<T> {

    private String message;
  
    private boolean sucess = true;
  
    private T data;
  
  
    public static ResponseBase<Void> empty() {
      return new ResponseBase<Void>()
        .setData(null)
        .setSucess(true);
    }
  
    public static <T> ResponseBase<T> of(final T data) {
      return new ResponseBase<T>()
        .setData(data)
        .setMessage("Operação realizada com sucesso.")
        .setSucess(true);
    }
  
    public static <T> ResponseBase<T> of(
      final T data,
      final String message,
      final boolean sucess
    ) {
      return new ResponseBase<T>()
        .setData(data)
        .setMessage(message)
        .setSucess(sucess);
    }
  
    public static <T> ResponseBase<T> success() {
      return new ResponseBase<T>()
        .setData(null)
        .setMessage("Operação realizada com sucesso.")
        .setSucess(true);
    }
  
    public String getMessage() {
      return this.message;
    }
  
    public ResponseBase<T> setMessage(final String message) {
      this.message = message;
      return this;
    }
  
    public boolean isSucess() {
      return this.sucess;
    }
  
    public ResponseBase<T> setSucess(final boolean sucess) {
      this.sucess = sucess;
      return this;
    }
  
    public T getData() {
      return this.data;
    }
  
    public ResponseBase<T> setData(final T entidade) {
      this.data = entidade;
      return this;
    }
  
  }
  
