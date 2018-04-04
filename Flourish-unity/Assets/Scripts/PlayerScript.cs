using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerScript : MonoBehaviour {

    private Vector3 currentMousePosition;
    private Vector3 updateMousePosition;
    private GameObject player;
    private Camera playerCamera;

    private int zoomState = 2;

    void Start()
    {
        player = GameObject.Find("Player");
        playerCamera = GameObject.Find("Player Camera").GetComponent<Camera>();
        updateMousePosition = new Vector3(0, 0, 0);
    }

    void Update()
    {
        checkMotion();
        checkZoom();
    }

    void checkMotion()
    {
        if (Input.GetMouseButtonDown(0))
        {
            currentMousePosition = Input.mousePosition;
        }
        else if (Input.GetMouseButton(0))
        {
            if (Input.mousePosition - updateMousePosition != new Vector3(0, 0, 0))
            {
                updateMousePosition = Input.mousePosition;
                Vector3 tempVector = new Vector3((currentMousePosition.x - Input.mousePosition.x) / (1500 / zoomState), 0, ((currentMousePosition.x - Input.mousePosition.x) * -0.575f / (1500 / zoomState)));
                Vector3 tempVector2 = new Vector3(0, (currentMousePosition.y - Input.mousePosition.y) * 3.46f / (1500 / zoomState), (currentMousePosition.y - Input.mousePosition.y) * -1.73f / (1500 / zoomState));
                player.transform.position += tempVector + tempVector2;
            }
            else
            {
                Vector3 tempVector = new Vector3((currentMousePosition.x - Input.mousePosition.x) / (4000 / zoomState), 0, ((currentMousePosition.x - Input.mousePosition.x) * -0.575f / (4000 / zoomState)));
                Vector3 tempVector2 = new Vector3(0, (currentMousePosition.y - Input.mousePosition.y) * 3.46f / (4000 / zoomState), (currentMousePosition.y - Input.mousePosition.y) * -1.73f / (4000 / zoomState));
                player.transform.position += tempVector + tempVector2;
            }
        }
    }

    void checkZoom()
    {
        if (Input.GetAxis("Mouse ScrollWheel") < 0f)
        {
            if (zoomState < 5)
            {
                zoomState++;
                playerCamera.orthographicSize = zoomState;
            }
        }
        else if (Input.GetAxis("Mouse ScrollWheel") > 0f)
        {
            if (zoomState > 1)
            {
                zoomState--;
                playerCamera.orthographicSize = zoomState;
            }
        }
    }
}
