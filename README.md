# ClickGUI
Simple DropDown ClickGUI for Minecraft hacked clients.
# Main Client Class
Your code of the main hacked client class should look like this. (Make sure SettingsManager is first!)
```java
private SettingsManager settingsManager;
private ClickGui clickGui;

public final void onLoadClient() {
    settingsManager = new SettingsManager();
    clickGui = new ClickGui();

public SettingsManager getSettingsManager() {
        return settingsManager;
    }

public ClickGui getClickGui() {
        return clickGui;
    }
```
# ClickGUI Module Class
Your code of the main ClickGUI Module class should look like this.
```java
@Override
    public void onEnable() {
        mc.displayGuiScreen(Flerovium.INSTANCE.getClickGui());
        toggle();
    }
}
```
# Finishing
You can now. use ClickGUI.
# Credits
- [Lemon ClickGUI](https://masterof13fps.com/forum/index.php?threads/lemon-clickgui-with-herocode-settings.5419)
- [HeroCode Setting system](https://www.mediafire.com/file/nb4jc813eax023b/HeroGUI_v2.zip/file)
# License
ClickGUI is licensed under GNU AGPLv3, a free and open-source license. For more information, please see the
[license file](https://github.com/Pandus1337/ClickGUI/blob/main/LICENSE).
