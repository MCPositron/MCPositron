package cn.nukkit.utils;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;

@PowerNukkitXOnly
public final class OptionalValue<T> {
    private static final OptionalValue<?> EMPTY = new OptionalValue<>(null);

    @Nullable private final T value;

    OptionalValue(@Nullable T value) {
        this.value = value;
    }

    public static <T> OptionalValue<T> of(T value) {
        return new OptionalValue<>(Objects.requireNonNull(value));
    }

    public static <T> OptionalValue<T> ofNullable(T value) {
        return value == null ? (OptionalValue<T>) EMPTY : of(value);
    }

    public static <T> OptionalValue<T> empty() {
        return (OptionalValue<T>) EMPTY;
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public T get() {
        return value;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public T orElseGet(Supplier<T> other) {
        return value != null ? value : other.get();
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public <X extends Throwable> T orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    @Override
    public String toString() {
        return value == null ? "OptionalValue.empty" : "OptionalValue[" + value + "]";
    }
}
