using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Invention : MonoBehaviour {

    public string title;
    public int id;
    public Image image;
    public double researchTime;
    public int changeInAirQuality = 0;
    public int changeInWaterQuality = 0;
    public int changeInSoilQuality = 0;

    public Invention(string title, int id, Image image)
    {
        this.title = title;
        this.id = id;
        this.image = image;
    }
}
