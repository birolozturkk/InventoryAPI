## Maven

https://jitpack.io/v/ScropyTR/LegendInventoryAPI.svg?style=flat-square

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>LegendInventoryAPI</artifactId>
    <version>Tag</version>
</dependency>

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```


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
