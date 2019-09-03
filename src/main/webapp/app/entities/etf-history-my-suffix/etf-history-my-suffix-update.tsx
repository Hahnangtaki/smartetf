import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etf-history-my-suffix.reducer';
import { IEtfHistoryMySuffix } from 'app/shared/model/etf-history-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfHistoryMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfHistoryMySuffixUpdateState {
  isNew: boolean;
}

export class EtfHistoryMySuffixUpdate extends React.Component<IEtfHistoryMySuffixUpdateProps, IEtfHistoryMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfHistoryEntity } = this.props;
      const entity = {
        ...etfHistoryEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-history-my-suffix');
  };

  render() {
    const { etfHistoryEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfHistory.home.createOrEditLabel">Create or edit a EtfHistory</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfHistoryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-history-my-suffix-id">ID</Label>
                    <AvInput id="etf-history-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="productIdLabel" for="etf-history-my-suffix-productId">
                    Product Id
                  </Label>
                  <AvField
                    id="etf-history-my-suffix-productId"
                    type="string"
                    className="form-control"
                    name="productId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="valueDateLabel" for="etf-history-my-suffix-valueDate">
                    Value Date
                  </Label>
                  <AvField id="etf-history-my-suffix-valueDate" type="date" className="form-control" name="valueDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitPriceLabel" for="etf-history-my-suffix-unitPrice">
                    Unit Price
                  </Label>
                  <AvField id="etf-history-my-suffix-unitPrice" type="string" className="form-control" name="unitPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitQtyLabel" for="etf-history-my-suffix-unitQty">
                    Unit Qty
                  </Label>
                  <AvField id="etf-history-my-suffix-unitQty" type="string" className="form-control" name="unitQty" />
                </AvGroup>
                <AvGroup>
                  <Label id="aumLabel" for="etf-history-my-suffix-aum">
                    Aum
                  </Label>
                  <AvField id="etf-history-my-suffix-aum" type="string" className="form-control" name="aum" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-history-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfHistoryEntity: storeState.etfHistory.entity,
  loading: storeState.etfHistory.loading,
  updating: storeState.etfHistory.updating,
  updateSuccess: storeState.etfHistory.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfHistoryMySuffixUpdate);
