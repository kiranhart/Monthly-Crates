# Monthly Crates
Ever wanted a crate plugin similar to that of the popular factions server CosmicPvP? Well here you go.
## Description
Monthly Crates is a plugin based of the server CosmicPvP, each crate has a total of 10 items. The first 9 items are the normal items, upon clicking one of them row and column animations will start to play while that clicked slot will cycle through all the possible rewards that can be set using the edit menu. Once all 9 main panes are clicked the final animations will start to cycle in (depends on how fast you click through the first slots). But once finished the final reward slot will be unlocked for you to get that reward. These crates are assigned to players by default meaning that if you give a player a crate, only they can open it unless stated otherwise within the config.

### API
```java
//Get the crate api instance
CrateAPI.getInstance()
//Here's a one useful method from that class
//Returns a list of crates as itemstacks
CrateAPI.getInstance().getListOfCrates() 
```
