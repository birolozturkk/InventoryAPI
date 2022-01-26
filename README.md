## Maven

[![](https://jitpack.io/v/ScropyTR/LegendInventoryAPI.svg)](https://jitpack.io/#ScropyTR/LegendInventoryAPI)

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>LegendInventoryAPI</artifactId>
    <version>1.1.6</version>
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
public class ExampleGUI extends GUI {

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
    ExampleGUI exampleGUI = new ExampleGUI(inventoryAPI, "exampleGUI", 54);

    ItemX item = new ItemX(new ItemStack(Material.GOLD), event -> {
        event.setCancelled(true);
    });

    exampleGUI.setItem(2, item);
    exampleGUI.addItem(item);

    exampleGUI.open(player);

```


### Paginated GUI

``` java
    ExampleGUI exampleGUI = new ExampleGUI(inventoryAPI, "exampleGUI", 54);
    Pagination pagination = exampleGUI.getPagination();

    List<ItemX> items = new ArrayList<>();

    for(int i = 0; i < 304; i++){
        int finalI = i;
        ItemX item = new ItemX(new ItemStack(Material.GOLD_BLOCK), event -> {
            event.getWhoClicked().sendMessage(String.valueOf(finalI));
        });
        items.add(item);
    }

    pagination.setSlots(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35));
    pagination.setItems(items);

    ItemX nextButton = new ItemX(new ItemStack(Material.PAPER), event -> {
        event.setCancelled(true);
        pagination.nextPage(event.getWhoClicked());
        event.getWhoClicked().sendMessage("sayfa : "+pagination.getCurrentPage());
    });

    ItemX previousButton = new ItemX(new ItemStack(Material.PAPER), event -> {
        event.setCancelled(true);
        pagination.previousPage(event.getWhoClicked());
        event.getWhoClicked().sendMessage("sayfa : "+pagination.getCurrentPage());
    });

    exampleGUI.setItem(42, nextButton);
    exampleGUI.setItem(40, previousButton);

    exampleGUI.open(player);

```

