package org.commercial_real_estate.model;

public enum District {
    INDUSTRIAL("Індустріальний"),
    KYIVSKY("Київський"),
    NEMYSHLYANSKY("Неми́шлянський"),
    NOVOBAVARSKY("Новобаварський"),
    OSNOVYANSKY("Основ'янський"),
    SALTIVSKY("Салтівський"),
    SLOBIDSKY("Слобідський"),
    KHOLODNOHIRSKY("Холодногірський"),
    SHEVCHENKIVSKY("Шевченківський");

    private final String name;

    District(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
