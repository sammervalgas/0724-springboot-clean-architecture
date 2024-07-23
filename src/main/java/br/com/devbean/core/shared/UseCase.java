package br.com.devbean.core.shared;

public interface UseCase<T, R> {
    R execute(T param);
}