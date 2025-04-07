using Unity.Netcode;
using UnityEngine;
using UnityEngine.InputSystem;

namespace MSE.Core
{
    public class PlayerMovement : NetworkBehaviour
    {
        private CharacterController m_CharacterController;

        [SerializeField] private float m_MoveSpeed = 5f;
        private Vector2 m_Direction;

        private void Awake()
        {
            m_CharacterController = GetComponent<CharacterController>();
        }

        private void FixedUpdate()
        {
            Vector3 movePos = new Vector3(m_Direction.x, 0f, m_Direction.y) * m_MoveSpeed;
            m_CharacterController.Move(movePos * Time.fixedDeltaTime);
        }

        public void OnMove(InputAction.CallbackContext context)
        {
            if (!IsOwner) return;

            m_Direction = context.ReadValue<Vector2>();

            if (m_Direction != null)
            {
                m_Direction.Normalize();
            }
        }
    }

}
