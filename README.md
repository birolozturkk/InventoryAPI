## Maven

[![](https://jitpack.io/v/ScropyTR/LegendInventoryAPI.svg)](https://jitpack.io/#ScropyTR/LegendInventoryAPI)

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>LegendInventoryAPI</artifactId>
    <version>3.0.7</version>
</dependency>

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```


## Setup

```java
InventoryAPI.setup(this);
```
Add to onEnable method of your plugin


## Usage

``` java
public class ExampleGUI extends GUI /*PaginatedGUI*/ {

    public ExampleGUI(InventoryAPI inventoryAPI, String title, int size) {
        super(inventoryAPI, title, size);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        super.onOpen(event);

        event.getPlayer().sendMessage("Inventory opened");

    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        super.onClose(event);

        event.getPlayer().sendMessage("Inventory closed");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        super.onClick(event);

        event.setCancelled(true);
    }
}
```


### Simple GUI

``` java

    //ExampleGUI extends GUI
    ExampleGUI exampleGUI = new ExampleGUI("Title", 27);
    
    Item item = new Item(XMaterial.CARROT).setDisplayName("display_name").setAmount(12)
            .setLore(Arrays.asList("line 1", "line 2", "line 3")).build();

    exampleGUI.setItem(item, 2);
    exampleGUI.addItem(item);

    exampleGUI.open(player);

```


### Paginated GUI

``` java

    //ExampleGUI extends PaginatedGUI
    ExampleGUI exampleGUI = new ExampleGUI("title", 54);

    List<Item> items = new ArrayList<>();
    for (int i = 0; i <= 105; i++) {
        Item item = new Item(XMaterial.CACTUS).build();
        item.setAction(clickEvent -> {
            clickEvent.getWhoClicked().sendMessage("clicked");
        });
        items.add(item);
    }

    exampleGUI.setSlots(Arrays.asList(0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17));
    exampleGUI.setItems(items);

    Item nextPageButton = new Item(XMaterial.PLAYER_HEAD).setDisplayName("next")
            .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE5MmFkNDU2Zjc2ZWM3Y2FhMzU5NTkyMmQ1ZmNjMzhkY2E5MjhkYzY3MTVmNzUyZTc0YzhkZGRlMzQ0ZSJ9fX0=")
            .setAction(clickEvent -> {
                exampleGUI.nextPage(player);
                clickEvent.getWhoClicked().sendMessage("Sayfa: " + exampleGUI.getCurrentPage());
            }).build();

    Item previousPageButton = new Item(XMaterial.PLAYER_HEAD).setDisplayName("previous")
            .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhNjA1ZTI1ZjRmYzJjZWE1YTc2NmQ3OWE4YmZhMjkwMzEzZTQ1ZDhmNWU5NTdkOTU4YTBmMzNmY2IxNiJ9fX0=")
            .setAction(clickEvent -> {
                exampleGUI.previousPage(player);
                clickEvent.getWhoClicked().sendMessage("Sayfa: " + exampleGUI.getCurrentPage());
            }).build();


    exampleGUI.setItem(nextPageButton, 23);
    exampleGUI.setItem(previousPageButton, 22);

    exampleGUI.open(player);

```

